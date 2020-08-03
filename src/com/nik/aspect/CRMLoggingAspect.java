package com.nik.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class CRMLoggingAspect {

    //setup logger
    private Logger myLogger = Logger.getLogger(getClass().getName());

    //setup pointcut
    @Pointcut("execution(* com.nik.controller.*.*(..))")
    private void forControllerPackage() {

    }

    //same for service and dao
    @Pointcut("execution(* com.nik.service.*.*(..))")
    private void forServicePackage() {

    }

    @Pointcut("execution(* com.nik.dao.*.*(..))")
    private void forDaoPackage() {

    }

    @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
    private void forAppFlow() {


    }

    //add @Before advice
    @Before("forAppFlow()")
    public void before(JoinPoint joinPoint) {
        //display method
        String method = joinPoint.getSignature().toShortString();
        myLogger.info("=====> @Before: calling method: " + method);

        //display args:
        //get args
        Object[] args = joinPoint.getArgs();

        //loop and display args
        for (Object tempArg : args) {
            myLogger.info("====>> argument: " + tempArg);
        }
    }

    //add @AfterReturning advice
    @AfterReturning(
            pointcut = "forAppFlow()",
            returning = "result"
    )
    public void afterReturning(JoinPoint joinPoint, Object result) {
        //display method
        String method = joinPoint.getSignature().toShortString();
        myLogger.info("=====> @AfterReturning: from method: " + method);

        //display data returned
        myLogger.info("=====>>> result: " + result);
    }

}
