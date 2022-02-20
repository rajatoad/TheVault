package com.revature.thevault.utility.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component("serviceAspect")
@Aspect
public class ServiceAspect {

    private final Logger dLog = LoggerFactory.getLogger("dLog");

    @Before("com.revature.thevault.utility.aspects.SystemArchitect.businessService()")
    public void beforeServiceCheck(JoinPoint jp){
        dLog.debug("CLASS: " + jp.getSignature().getDeclaringType() + "\nMETHOD: " + jp.getSignature().getName() + "\nARGUMENTS: " + Arrays.toString(jp.getArgs()));
    }

    @AfterReturning(value = "com.revature.thevault.utility.aspects.SystemArchitect.businessService()", returning = "returnedValue")
    public void afterServiceCheck(JoinPoint jp, Object returnedValue){
        if(returnedValue != null){
            dLog.info("METHOD: " + jp.getSignature().getName() + "\nRETURNING: " + returnedValue);
        }else{
            dLog.info("METHOD: " + jp.getSignature().getName() + "\nRETURNING: NULL VALUE");
        }
    }

    @AfterThrowing(value = "com.revature.thevault.utility.aspects.SystemArchitect.businessService()", throwing = "thrownException")
    public void afterThrowingCheck(JoinPoint jp, Exception thrownException){
        dLog.error("CONTROLLER CLASS: " + jp.getSignature().getDeclaringType() + "\nMETHOD: " + jp.getSignature().getName() + "\nTHROWING EXCEPTION CLASS: " + thrownException.getClass() + "\nEXCEPTION STACKTRACE: " + thrownException.getStackTrace());
    }
}
