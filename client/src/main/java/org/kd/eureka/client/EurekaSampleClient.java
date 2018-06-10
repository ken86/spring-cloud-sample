package org.kd.eureka.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@EnableEurekaClient
@ImportResource("classpath:spring/spring-integration-file.xml")
public class EurekaSampleClient implements CommandLineRunner {

    @Autowired
    JournalService journalService;

    public static void main(String[] args) {
        SpringApplication.run(EurekaSampleClient.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        journalService.insertData();
    }
}
