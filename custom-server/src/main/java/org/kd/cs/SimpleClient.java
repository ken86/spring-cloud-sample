package org.kd.cs;

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
import java.util.Set;

public class SimpleClient {

    private static final int PORT= 9090;

    private static final String host = "localhost";

    private static ByteBuffer byteBuffer = ByteBuffer.allocate(256);

    public static void main(String[] args){

        try (Selector selector = Selector.open();
             SocketChannel socketChannel = SocketChannel.open()) {

            Charset charset = Charset.defaultCharset();
            CharsetDecoder decoder = charset.newDecoder();

            if (socketChannel.isOpen() && selector.isOpen()) {
                socketChannel.configureBlocking(false);
                socketChannel.register(selector, SelectionKey.OP_CONNECT);
                socketChannel.connect(new InetSocketAddress(host, PORT));

                while(selector.select(1000) > 0) {
                    Set<SelectionKey> keys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = keys.iterator();
                    while(iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        iterator.remove();

                        int numRead;
                        try (SocketChannel respSocketChannel =(SocketChannel) key.channel()) {
                            if (key.isConnectable()) {
                                System.out.println("connected");
                                if (respSocketChannel.isConnectionPending()) {
                                    respSocketChannel.finishConnect();
                                }

                                while ((respSocketChannel.read(byteBuffer)) != -1) {
                                    byteBuffer.flip();
                                    CharBuffer charBuffer = decoder.decode(byteBuffer);
                                    System.out.println(charBuffer.toString());

                                    if (byteBuffer.hasRemaining()) {
                                        byteBuffer.compact();
                                    } else {
                                        byteBuffer.clear();
                                    }
                                    //Thread.currentThread().sleep(1000);
                                    System.out.println("close connection");
                                    break;
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
}
