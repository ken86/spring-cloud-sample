package org.kd.ms.aop.annotation;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class ExampleAspect {

    @Around("@annotation(org.kd.ms.aop.annotation.PrintStartEnd)")
    public Object printStartEnd(ProceedingJoinPoint joinPoint) throws Throwable {

        System.out.println("print before: " + joinPoint.getSignature());

        final Object proceed = joinPoint.proceed();

        System.out.println("print after: " + joinPoint.getSignature());

        return proceed;
    }
}
