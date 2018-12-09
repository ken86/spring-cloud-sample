package org.kd.cmd.server.executor;

import org.kd.cmd.server.command.ConcurrentCommand;

import java.util.concurrent.FutureTask;

public class ServerTask<V> extends FutureTask<V> {

    private ConcurrentCommand concurrentCommand;


    public ServerTask(ConcurrentCommand concurrentCommand) {
        super(concurrentCommand, null);
        this.concurrentCommand = concurrentCommand;
    }

    public ConcurrentCommand getConcurrentCommand() {
        return concurrentCommand;
    }

    public void setConcurrentCommand(ConcurrentCommand concurrentCommand) {
        this.concurrentCommand = concurrentCommand;
    }
}
