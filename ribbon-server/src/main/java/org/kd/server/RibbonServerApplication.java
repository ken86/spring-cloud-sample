package org.kd.server;

import org.kd.server.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableEurekaClient
@SpringBootApplication
@RestController
public class RibbonServerApplication implements CommandLineRunner {

    @Autowired
    BookService bookService;

    public static void main(String[] args) {
        SpringApplication.run(RibbonServerApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        bookService.initData();
    }

    @RequestMapping("/bookService/hello")
    public String helloService() {
        return "hello world";
    }
}
