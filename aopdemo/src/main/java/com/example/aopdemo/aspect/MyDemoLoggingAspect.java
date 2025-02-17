package com.example.aopdemo.aspect;

import com.example.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

    //add new advice for @AfterReturning on the findAccounts method
    @AfterReturning(
            pointcut = "execution(* com.example.aopdemo.dao.AccountDAO.findAccounts(..))",
            returning = "result"
    )
    public void afterReturningFindAccountsAdvice(JoinPoint thJoinPoint, List<Account> result){
        //print method
        String method = thJoinPoint.getSignature().toShortString();
        System.out.println("\n======>> executing @AfterReturning on method "+method);
        //print results of method call
        System.out.println("\n======>> result is "+ result);
    }

    //@Before("execution(public void add*())")
    @Before("com.example.aopdemo.aspect.AopExpressions.forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice(JoinPoint theJoinPoint){
        System.out.println("\n=====>>> executing @Before advice on addAccount()");

        //display method signature
        MethodSignature methodSignature = (MethodSignature) theJoinPoint.getSignature();
        System.out.println("method: "+ methodSignature);
        //display method args
        Object[] args = theJoinPoint.getArgs();
        for(Object o : args){
            System.out.println(o);
            if(o instanceof Account){
                Account theAccount = (Account)o;
                System.out.println("account name "+theAccount.getName());
                System.out.println("account leve "+theAccount.getLevel());
            }
        }
    }

}
