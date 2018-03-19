package org.kd.eureka.client;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloControllerImpl implements HelloController {

    @Override
    @RequestMapping("/hello")
    public String hello() {
        return "Hello from Eureka Client";
    }
}
