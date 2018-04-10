package org.kd.eureka.aop.annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        ProceedService service = (ProceedService) applicationContext.getBean("proceedService");
        service.service();

    }
}
