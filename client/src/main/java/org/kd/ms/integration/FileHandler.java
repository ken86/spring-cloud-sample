package org.kd.ms.integration;


import org.springframework.messaging.Message;

import java.io.File;

public class FileHandler {


    public Message<File> handleFile(final File file) {
        System.out.println(file.getName());
        return null;
    }
}
