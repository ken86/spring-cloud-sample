package org.kd.cmd.server.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class NioSimpleClient {


    public static void main(String[] args) {

        ByteBuffer buffer = ByteBuffer.allocateDirect(2 * 1024);
        ByteBuffer randomBuffer;
        CharBuffer charBuffer;
        Charset charset = Charset.defaultCharset();
        CharsetDecoder decoder = charset.newDecoder();

        try(Selector selector = Selector.open(); SocketChannel socketChannel = SocketChannel.open()) {
            if (socketChannel.isOpen() && selector.isOpen()) {
                socketChannel.configureBlocking(false);

                socketChannel.register(selector, SelectionKey.OP_CONNECT);
                socketChannel.connect(new InetSocketAddress("localhost", 9090));

                System.out.println("Localhost: " + socketChannel.getLocalAddress());

                while(selector.select() > 0) {
                    Set keys = selector.selectedKeys();
                    Iterator iter = keys.iterator();

                    while(iter.hasNext()) {
                        SelectionKey key = (SelectionKey) iter.next();
                        iter.remove();

                        try (SocketChannel keySocketChannel = (SocketChannel) key.channel()) {
                            if (key.isConnectable()) {
                                //signal connection success
                                System.out.println("I am connected!");
                                //close pending connections
                                if (keySocketChannel.isConnectionPending()) {
                                    keySocketChannel.finishConnect();
                                }

                                while(keySocketChannel.read(buffer) != -1) {
                                    buffer.flip();
                                    charBuffer = decoder.decode(buffer);
                                    System.out.println(charBuffer.toString());
                                    if (buffer.hasRemaining()) {
                                        buffer.compact();
                                    } else {
                                        buffer.clear();
                                    }
                                    int r = new Random().nextInt(10);
                                    if (r == 5) {
                                        System.out.println("5 was generated! Close the socket channel!");
                                        System.exit(0);
                                    } else {
                                        randomBuffer = ByteBuffer.wrap("Random number:"
                                                .concat(String.valueOf(r)).getBytes("UTF-8"));
                                        keySocketChannel.write(randomBuffer);
                                        System.out.println("send " + r);
                                        try {
                                            Thread.sleep(1500);
                                        } catch (InterruptedException ex) {
                                        }
                                    }
                                }

                            }
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }

                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }






//    public static void main(String[] args) {
//        SocketChannel socketChannel = null;
//        try {
//            socketChannel = SocketChannel.open();
//            socketChannel.configureBlocking(false);
//            socketChannel.connect(new InetSocketAddress("localhost", 9999));
//            while(!socketChannel.finishConnect()) {
//                System.out.println("waiting to finish connection");
//            }
//            ByteBuffer byteBuffer = ByteBuffer.allocate(256);
//            while((socketChannel.read(byteBuffer)) >= 0) {
//                byteBuffer.flip();
//                while(byteBuffer.hasRemaining()) {
//                    System.out.print((char)byteBuffer.get());
//                }
//                byteBuffer.clear();
//            }
//            socketChannel.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                socketChannel.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}
