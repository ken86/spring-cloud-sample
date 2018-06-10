package org.kd.ms.client;

import org.springframework.web.bind.annotation.RequestMapping;

public interface HelloController {

    @RequestMapping("/hello")
    String hello();
}
