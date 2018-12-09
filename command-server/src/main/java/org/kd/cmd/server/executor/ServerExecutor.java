package org.kd.cmd.server.executor;

import org.kd.cmd.server.command.Command;
import org.kd.cmd.server.command.ConcurrentCommand;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ServerExecutor extends ThreadPoolExecutor {

    private static int CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors();

    private static int KEEP_ALIVE_TIME = 10;

    private static int MAX_POOL_SIZE = Runtime.getRuntime().availableProcessors();

    public ServerExecutor() {
        super(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS, new ArrayBlockingQueue(16));
    }

//    @Override
//    protected void afterExecute(Runnable r, Throwable t) {
//        super.afterExecute(r, t);
//
//        ServerTask<?> task = (ServerTask<?>) r;
//        ConcurrentCommand concurrentCommand = task.getConcurrentCommand();
//
//        if (t == null && !task.isCancelled()) {
//
//        }
//    }
}
