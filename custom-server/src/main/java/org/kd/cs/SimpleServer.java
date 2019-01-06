package org.kd.cs;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class SimpleServer {

    private static final int PORT = 9090;

    private static boolean stop = false;

    private static ByteBuffer bb = ByteBuffer.allocate(256);

    public static void main(String[] args) {
        try (Selector selector = Selector.open();
             ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()){
            ServerSocket serverSocket = serverSocketChannel.socket();
            serverSocket.bind(new InetSocketAddress(PORT));
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            System.out.println("server ready");
            while(true) {
                selector.select();
                System.out.println("incoming select");

                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

                while(iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    if (!key.isValid()) {
                        continue;
                    }
                    iterator.remove();

                    if (key.isAcceptable()) {
                        System.out.println("Accept key");
                        ServerSocketChannel ssc = ((ServerSocketChannel)key.channel());
                        SocketChannel sc = ssc.accept();
                        sc.configureBlocking(false);
                        sc.write(ByteBuffer.wrap("Server Accepted".getBytes()));
                        sc.register(selector, SelectionKey.OP_READ);
                    } else if (key.isReadable()) {
                        System.out.println("Read key");
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        int n = 0;
                        try {
                            n = socketChannel.read(bb);
                        } catch (IOException ex) {
                            System.out.println("read error");
                            ex.printStackTrace();
                        }
                        if (n == -1) {
                            System.out.println("close");
                            key.cancel();
                        }
                    }
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
