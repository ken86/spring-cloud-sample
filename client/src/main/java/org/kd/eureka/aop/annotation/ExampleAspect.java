package org.kd.eureka.aop.annotation;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class ExampleAspect {

    @Around("@annotation(org.kd.eureka.aop.annotation.PrintStartEnd)")
    public Object printStartEnd(ProceedingJoinPoint joinPoint) throws Throwable {

        final Object proceed = joinPoint.proceed();

        System.out.println(joinPoint.getSignature());

        return proceed;
    }
}
