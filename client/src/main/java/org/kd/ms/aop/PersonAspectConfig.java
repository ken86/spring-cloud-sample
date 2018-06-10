package org.kd.ms.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@Aspect
public class PersonAspectConfig {

    @Before("execution (* org.kd.ms.aop.Person.setName(..))")
    public void validate(){
        System.out.println("validate Person setting name");
    }

    @Bean
    public Person person() {
        return new Person("XXX", "ID-NE-123C");
    }
}
