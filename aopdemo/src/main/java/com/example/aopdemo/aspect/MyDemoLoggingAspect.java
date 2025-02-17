package com.example.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {
    //advices for logging

    @Pointcut("execution(* com.example.aopdemo.dao.*.*(..))")
    private void forDaoPackage(){}

    //pointcut declaration for getter methods
    @Pointcut("execution(* com.example.aopdemo.dao.*.get*(..))")
    private void getter(){}

    //pointcut declaration for setter methods
    @Pointcut("execution(* com.example.aopdemo.dao.*.set*(..))")
    private void setter(){}

    //pointcut to include package excluding getters and setters
    @Pointcut("forDaoPackage() && !(getter() || setter())")
    private void forDaoPackageNoGetterSetter(){}

    //@Before("execution(public void add*())")
    @Before("forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice(){
        System.out.println("\n=====>>> executing @Before advice on addAccount()");
    }

    @Before("forDaoPackageNoGetterSetter()")
    public void performApiAnalytics(){
        System.out.println("\n=====>>> performing api analytics");
    }
}
