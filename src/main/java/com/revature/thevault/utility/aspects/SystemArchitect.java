//package com.revature.thevault.utility.aspects;
//
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.stereotype.Component;
//
//@Component("systemArchitect")
//@Aspect
//public class SystemArchitect {
//
//    @Pointcut("within(com.revature.thevault.service..*)")
//    public void inService(){}
//
//    @Pointcut("within(com.revature.thevault.repository..*)")
//    public void publicOperations() {}
//
//    @Pointcut("execution(* com.revature.thevault.service..*.*(..))")
//    public void businessService(){}
//
//    @Pointcut("execution(* com.revature.thevault.repository..*.*(..))")
//    public void dataAccessOperation(){}
//
//    @Pointcut("execution(* com.revature.thevault.presentation..*.*(..))")
//    public void controller(){}
//
//
//}
