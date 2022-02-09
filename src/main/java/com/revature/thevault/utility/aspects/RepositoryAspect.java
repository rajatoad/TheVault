package com.revature.thevault.utility.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class RepositoryAspect {

    private final Logger dLog = LoggerFactory.getLogger("dLog");

    @Before("com.revature.p2_lfg.utility.SystemArchitect.dataAccessOperation()")
    public void beforeServiceCheck(JoinPoint jp){
        dLog.debug("Class: " + jp.getSignature().getDeclaringType() + "\nMethod: " + jp.getSignature().getName() + "\nArguments: " + Arrays.toString(jp.getArgs()));
    }

    @AfterReturning(value = "com.revature.p2_lfg.utility.SystemArchitect.dataAccessOperation()", returning = "returnedValue")
    public void afterServiceCheck(JoinPoint jp, Object returnedValue) {
        if (returnedValue != null) {
            dLog.info("Class: " + jp.getSignature().getDeclaringType() + "\nReturning: " + returnedValue.toString());
        }else{
            dLog.info("Class: " + jp.getSignature().getDeclaringType() + "\nReturning: " + "null");
        }
    }

    @AfterThrowing(value = "com.revature.p2_lfg.utility.SystemArchitect.dataAccessOperation()", throwing = "thrownException")
    public void afterThrowingCheck(JoinPoint jp, Object thrownException){
        dLog.error("Class: " + jp.getSignature().getDeclaringType() + "\nThrowing: " + thrownException);
    }
}


