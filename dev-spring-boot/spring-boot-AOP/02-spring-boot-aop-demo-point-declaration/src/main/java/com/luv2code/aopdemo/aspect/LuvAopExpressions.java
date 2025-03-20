package com.luv2code.aopdemo.aspect;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LuvAopExpressions {
    @Pointcut("execution(* com.luv2code.aopdemo.*.*(..))")
    public void forDaoPackage(){}

    // create a pointcut for getter methods
    @Pointcut("execution(* com.luv2code.aopdemo.*.get*(..))")
    public void getter(){}

    // create a pointcut fot setter methods
    @Pointcut("execution(* com.luv2code.aopdemo.*.set*(..))")
    public void setter(){}


    //create pointcut :include package ... exclude getter/setter
    @Pointcut("forDaoPackage() && !(getter() || setter())")
    public void forDaoPackageNoGetterSetter(){}
}
