package org.kd.cmd.server.command;

import java.net.Socket;

public abstract class ConcurrentCommand<T> implements Command, Runnable {

    @Override
    public abstract T execute();

    private Socket socket;

    private String command;

    public ConcurrentCommand(Socket socket, String command) {
        this.socket = socket;
        this.command = command;
    }

    @Override
    public void run() {
        execute();
        System.out.println("command executed successfully");

    }
}
