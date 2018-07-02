package org.kd.ms.integration.inotify;

import org.springframework.integration.endpoint.MessageProducerSupport;
import org.springframework.integration.file.FileHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.Assert;

import java.io.File;
import java.util.concurrent.Executor;

public class DirMonitorInboundFileEndpoint extends MessageProducerSupport {

    private File directoryToMonitor;
    private DirMonitor monitor;
    private Executor executor;
    private int queueSize = 10;

    @Override
    protected void onInit() {
        try {
            DirMonitorImpl mon = new DirMonitorImpl();
            if (executor != null) {
                mon.setExecutor(executor);
            }
            mon.afterPropertiesSet();
            this.monitor = mon;

        } catch (Exception e) {
            throw new RuntimeException("Exception thrown when trying to setup "
                    + DirMonitorImpl.class, e);
        }
    }

    protected void doStart() {
        Assert.notNull(monitor, "the monitor can't be null");
        MessageProducingFileAddedListener messageProducingFileAddedListener =
                new MessageProducingFileAddedListener();
        monitor.monitor(directoryToMonitor, messageProducingFileAddedListener);
    }

    @Override
    protected void doStop() {
    }

    public void setDirectoryToMonitor(File directoryToMonitor) {
        this.directoryToMonitor = directoryToMonitor;
    }

    class MessageProducingFileAddedListener
            implements DirMonitor.FileAddedListener {
        @Override
        public void fileAdded(File dir, String fn) {
            File fi = new File(dir, fn);
            Message<File> msg =
                    MessageBuilder.withPayload(fi).setHeader(
                            FileHeaders.FILENAME, fi.getPath()).build();
            sendMessage(msg);
        }
    }
}
