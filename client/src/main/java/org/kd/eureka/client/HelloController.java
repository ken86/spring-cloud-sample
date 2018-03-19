package org.kd.eureka.client;

import org.springframework.web.bind.annotation.RequestMapping;

public interface HelloController {

    @RequestMapping("/hello")
    String hello();
}
