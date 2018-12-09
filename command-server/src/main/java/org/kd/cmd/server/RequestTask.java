package org.kd.cmd.server;

import org.kd.cmd.server.command.Command;
import org.kd.cmd.server.command.ConcurrentCommand;
import org.kd.cmd.server.command.ConcurrentQueryCommand;
import org.kd.cmd.server.executor.ServerExecutor;
import org.kd.cmd.server.executor.ServerTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;

public class RequestTask implements Runnable{

    private LinkedBlockingDeque<Socket> pendingConnections;

    private ConcurrentHashMap<String, ConcurrentHashMap<Command, ServerTask<?>>> taskController;

    private ServerExecutor executor = new ServerExecutor();

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    ConcurrentCommand concurrentCommand;

                    Socket clientSocket = pendingConnections.take();
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    String line = in.readLine();

                    if (line.equals("QUERY")) {
                        concurrentCommand = new ConcurrentQueryCommand(clientSocket, line);
                        break;
                    } else {
                        concurrentCommand = new ConcurrentQueryCommand(clientSocket, line);
                    }

                    ServerTask<?> controller = (ServerTask<?>)executor.submit(concurrentCommand);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (Exception ex) {

        }
    }
}
