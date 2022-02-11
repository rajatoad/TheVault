package com.revature.thevault.utility.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component("presentationAspect")
@Aspect
public class PresentationAspect {

    private final Logger dLog = LoggerFactory.getLogger("dLog");

    @Before("com.revature.thevault.utility.aspects.SystemArchitect.controller()")
    public void beforeServiceCheck(JoinPoint jp){
        dLog.debug("Class: " + jp.getSignature().getDeclaringType() + "\nMethod: " + jp.getSignature().getName() + "\nArguments: " + Arrays.toString(jp.getArgs()));
    }

    @AfterReturning(value = "com.revature.thevault.utility.aspects.SystemArchitect.controller()", returning = "returnedValue")
    public void afterServiceCheck(JoinPoint jp, Object returnedValue){
        if(returnedValue != null){
            dLog.info("RETURNING: " + returnedValue);
        }else{
            dLog.info("Returning: NULL VALUE");
        }
    }

    @AfterThrowing(value = "com.revature.thevault.utility.aspects.SystemArchitect.controller()", throwing = "thrownException")
    public void afterThrowingCheck(JoinPoint jp, Object thrownException){
        dLog.error("CONTROLLER CLASS: " + jp.getSignature().getDeclaringType() + "\nTHROWING EXCEPTION: " + thrownException.getClass());
    }
}
