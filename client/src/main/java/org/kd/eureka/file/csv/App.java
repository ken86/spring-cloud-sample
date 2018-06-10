package org.kd.eureka.file.csv;


import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

public class App {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("classpath:spring/csv-file-writer.xml");
        CsvFileWriter writer = (CsvFileWriter) applicationContext.getBean("csvFileWriter");
        Employee employee = new Employee();
        employee.setId("staff-id-1");
        employee.setName("jack");
        employee.setDepartment("IT");
        writer.write(Arrays.asList(employee));
    }
}
