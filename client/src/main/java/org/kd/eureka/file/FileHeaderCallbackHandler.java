package org.kd.eureka.file;

import org.springframework.batch.item.file.FlatFileHeaderCallback;

import java.io.IOException;
import java.io.Writer;

public class FileHeaderCallbackHandler implements FlatFileHeaderCallback {

    private String header;

    @Override
    public void writeHeader(Writer writer) throws IOException {
        writer.write(header);
    }

    public void setHeader(String header) {
        this.header = header;
    }
}
