package com.luv2code.aopdemo.aspect;

import com.luv2code.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

    @Around( "execution(* com.luv2code.aopdemo.service.AccountDAO.findAccounts(..))")

    public Object aroundGteFortune(ProceedingJoinPoint theProceedingJoinPont)
        throws Throwable{

        // print out method we are advising on
        String method = theProceedingJoinPont.getSignature().toShortString();
        System.out.println("\n=====> Executing @Around on method :"+method);

        //get begin timestamp
        long begin = System.currentTimeMillis();

        //now,let's execute the method
        Object result =null;

        try {
           result = theProceedingJoinPont.proceed();
        }catch (Exception exc){
            // log teh exception
            System.out.println(exc.getMessage());

            // give user a custom message
            //delete this line
            // result = "Major accident! But no worries,your private AOP helicopter is on the way!";

            //rethrow exception
            throw exc;

        }

        //get end timestamp
        long end = System.currentTimeMillis();

        //compute duration and display it

        long duration = end-begin;
        System.out.println("\n=====> Duration" + duration / 1000.0 + " seconds");
        return result;

    }

    @After( "execution(* com.luv2code.aopdemo.DAO.*.getFortune(..))")
    public void afterFinallyAccountsAdvice(JoinPoint theJoinPoint) {

        // print out which method we are advice
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n=====> Executing @After (finally) on method :"+method);
    }

    @AfterThrowing(
            pointcut = "execution(* com.luv2code.aopdemo.DAO.AccountDAO.findAccounts(..))",
            throwing = "theExc"
    )
    public void afterThrowFindAccountsAdvice(  JoinPoint theJoinPoint,
                                               Throwable theExc){

        // print out which method we are advice
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n=====> Executing @AfterThrowing on method :"+method);


        // log the exception
        System.out.println("\n=====> The exception is :"+theExc);

    }

    // this is where we add all of our related advices for logging
    // let's start with an @Before advice

    //@Before("execution(public void com.luv2code.aopdemo.AccountDAO.addAccount())")
    //@Before("execution(public void add*())")
    //@Before("execution(* add*(com.luv2code.aopdemo.Account,..))")
    //@Before("execution(* com.luv2code.aopdemo.*.*(..))")


    // add a new advice for @AfterReturning on the findAccounts method
    @AfterReturning(
            pointcut = "execution(* com.luv2code.aopdemo.DAO.AccountDAO.findAccounts(..))",
            returning = "result"
    )
    public void afterReturningFindAccountsAdvice(JoinPoint theJoinPoint, List<Account> result){

        // print out which method we are advising on
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n=====> Executing @AfterReturning on method :"+method);

        // print out the results of the method call
        System.out.println("\n=====> result is :"+result);

        // let's post-process the data ... let's modify it :-

        //convert the account names to uppercase
        convertAccountToUpperCase(result);
        System.out.println("\n=====> result is :"+result);

    }

    private void convertAccountToUpperCase(List<Account> result) {

        // loop throw accounts
        for (Account tempAccount : result) {

            // get uppercase version of name
            String theUpperName = tempAccount.getName().toUpperCase();

            // update the name on the account
            tempAccount.setName(theUpperName);
        }
    }
    @Before("com.luv2code.aopdemo.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice(JoinPoint theJoinPoint){
        System.out.println("\n=====>>>Executing @Before advice on addAccount");

        //display the method signature
        MethodSignature methodSignature = (MethodSignature) theJoinPoint.getSignature();
        System.out.println("Method:"+methodSignature);

        //display method arguments

        // get args
        Object [] args = theJoinPoint.getArgs();

        // loop thru args
        for (Object tempArg : args){
            System.out.println(tempArg);

            if(tempArg instanceof Account){

                // downcast and print Account specific stuff
                Account theAccount = (Account) tempArg;

                System.out.println("account name: "+theAccount.getName());
                System.out.println("account level: "+theAccount.getLevel());

            }
        }

    }

    }


