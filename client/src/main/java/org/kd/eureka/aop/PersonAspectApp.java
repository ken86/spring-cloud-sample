package org.kd.eureka.aop;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PersonAspectApp {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(PersonAspectConfig.class);

        Person person = (Person) applicationContext.getBean("person");
        System.out.println("before set name");
        person.setName("ABC");
        System.out.println("after set name");
    }
}
