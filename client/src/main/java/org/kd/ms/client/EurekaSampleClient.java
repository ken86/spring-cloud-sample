package org.kd.ms.client;

import org.kd.ms.client.restful.RestSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
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
