package org.kd.eureka.file.csv;

import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.integration.file.FileNameGenerator;

public class CsvFileWriter  {

    private String path;
    private FlatFileItemWriter flatFileItemWriter;
    private FileNameGenerator fileNameGenerator;

    public CsvFileWriter(final String path) {
        this.path = path;
    }

    public void setFlatFileItemWriter(FlatFileItemWriter flatFileItemWriter) {
        this.flatFileItemWriter = flatFileItemWriter;
    }

    public void setFileNameGenerator(FileNameGenerator fileNameGenerator) {
        this.fileNameGenerator = fileNameGenerator;
    }
}
