package com.revature.thevault.utility.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("presentationAspect")
@Aspect
public class PresentationAspect {

    private final Logger dLog = LoggerFactory.getLogger("dLog");

    @Before("com.revature.thevault.utility.aop.SystemArchitect.controller()")
    public void beforeServiceCheck(JoinPoint jp){
        dLog.debug("CONTROLLER CLASS: " + jp.getSignature().getDeclaringType() + "\nMETHOD: " + jp.getSignature().getName());
    }

    @AfterReturning(value = "com.revature.thevault.utility.aop.SystemArchitect.controller()", returning = "returnedValue")
    public void afterServiceCheck(JoinPoint jp, Object returnedValue){
        if(returnedValue != null){
            dLog.info("RETURNING: " + returnedValue.toString());
        }else{
            dLog.info("Returning: NULL VALUE");
        }
    }

    @AfterThrowing(value = "com.revature.thevault.utility.SystemArchitect.controller()", throwing = "thrownException")
    public void afterThrowingCheck(JoinPoint jp, Object thrownException){
        dLog.error("CONTROLLER CLASS: " + jp.getSignature().getDeclaringType() + "\nTHROWING EXCEPTION: " + thrownException.getClass());
    }
}
