package org.kd.ms.integration.inotify;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public static void main(String[] args) {
        //System.out.println(System.getProperty("java.library.path"));
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-inotify.xml");

    }
}
