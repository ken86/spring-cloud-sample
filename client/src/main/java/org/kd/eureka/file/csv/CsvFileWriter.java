package org.kd.eureka.file.csv;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.integration.file.FileNameGenerator;

import java.util.List;

public class CsvFileWriter  {

    private String path;
    private FlatFileItemWriter flatFileItemWriter;
    private FileNameGenerator fileNameGenerator;


    public CsvFileWriter(final String path) {
        this.path = path;
    }

    public void write(final List<Employee> employees) throws Exception {
        flatFileItemWriter.setResource(new FileSystemResource("/Users/hksgr/data/a.csv"));
        flatFileItemWriter.open(new ExecutionContext());
        flatFileItemWriter.write(employees);
    }


    public void setFlatFileItemWriter(FlatFileItemWriter flatFileItemWriter) {
        this.flatFileItemWriter = flatFileItemWriter;
    }

    public void setFileNameGenerator(FileNameGenerator fileNameGenerator) {
        this.fileNameGenerator = fileNameGenerator;
    }
}
