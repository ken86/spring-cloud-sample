package org.kd.cmd.server.command;

import java.net.Socket;

public class ConcurrentQueryCommand extends ConcurrentCommand {

    public ConcurrentQueryCommand(Socket socket, String command) {
        super(socket, command);
    }

    @Override
    public String execute() {
        System.out.println("execute the query...");
        return "done";
    }
}
