package org.kd.ms.robbin;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@SpringBootApplication
@EnableEurekaClient
@RestController
public class SpringCloudRobbinServerApp {

    private static final Logger logger = LoggerFactory.getLogger(SpringCloudRobbinServerApp.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudRobbinServerApp.class, args);
    }

    @RequestMapping("/hello")
    public String helloService() {
        logger.info("response from: " + this);
        return "Hello";
    }

}
