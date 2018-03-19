package org.kd.eureka.integration;

import org.springframework.integration.core.MessageSource;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

import java.io.File;

public class ACompositeFileInboundPoller implements MessageSource<File> {

    private File directory = new File("f://test//");

    public File getSingleFile() {
        File file = null;
        if (!directory.isDirectory()) {
            System.out.println("Directory needed");
        }
        return scanFile(file);
    }

    public File scanFile(File file) {
        File[] files = file.listFiles();
        return files[0];
    }

    @Override
    public Message<File> receive() {
        System.out.println("received");
        return MessageBuilder.withPayload(getSingleFile()).build();
    }
}
