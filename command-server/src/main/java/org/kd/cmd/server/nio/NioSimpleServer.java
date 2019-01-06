package org.kd.cmd.server.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NioSimpleServer {

    public static void main(String[] args) {

        try (Selector selector = Selector.open(); ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().bind(new InetSocketAddress(9090));
            ByteBuffer byteBuffer = ByteBuffer.allocate(256);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            while (true) {
                int n = selector.select();
                System.out.println("incoming select");
                if (n == 0) {
                    continue;
                }

                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    iterator.remove();

                    if (!selectionKey.isValid()) {
                        continue;
                    }

                    if (selectionKey.isAcceptable()) {
                        ServerSocketChannel serverChannel = (ServerSocketChannel) selectionKey.channel();
                        SocketChannel socketChannel = serverChannel.accept();
                        socketChannel.configureBlocking(false);

                        System.out.println("RC connection");

                        socketChannel.write(ByteBuffer.wrap("Hello Guest".getBytes()));
                        socketChannel.register(selector, SelectionKey.OP_READ);
                    } else if (selectionKey.isReadable()) {
                        System.out.println("readable");

                        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                        byteBuffer.clear();
                        int numRead = -1;
                        try {
                            numRead = socketChannel.read(byteBuffer);
                            System.out.println(numRead);
                        } catch (IOException e) {
                            System.err.println("Cannot read error!");
                        }

                        if (numRead == -1) {
                            System.out.println("Connection closed by: " + socketChannel.getRemoteAddress());
//                            socketChannel.close();
                            selectionKey.cancel();
                            continue;
                        }

                        byte[] data = new byte[numRead];
                        System.arraycopy(byteBuffer.array(), 0, data, 0, numRead);
                        System.out.println(new String(data, "UTF-8") + " from " + socketChannel.socket().getRemoteSocketAddress());
                    }

                }
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }


    }

}
