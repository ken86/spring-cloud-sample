package org.kd.ms.file;

import org.springframework.messaging.Message;

public class FileNameGenerator implements org.springframework.integration.file.FileNameGenerator {

    @Override
    public String generateFileName(Message<?> message) {
        return "Generated_File_Name";
    }
}
