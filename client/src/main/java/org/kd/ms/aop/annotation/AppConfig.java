package org.kd.ms.aop.annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AppConfig {

    @Bean
    public ProceedService proceedService() {
        return new ProceedService();
    }

    @Bean
    public ExampleAspect exampleAspect() {
        return new ExampleAspect();
    }
}
