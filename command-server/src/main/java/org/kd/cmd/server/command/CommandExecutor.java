package org.kd.cmd.server.command;

public class CommandExecutor {

    private Command command;

    public CommandExecutor (Command command) {
        this.command = command;
    }

    public void exec() {
        this.command.execute();
    }
}
