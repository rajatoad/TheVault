package com.revature.thevault.utility.validation;

import com.revature.thevault.service.exceptions.InvalidRequestException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component("requestTypeValidation")
@Aspect
public class RequestTypeValidation {
    @Pointcut("this(com.revature.thevault.service.classes.RequestTypeService)")
    private void requestTypeService(){}

    @Pointcut("args(requestType)")
    private void requestType(String requestType){}

    @Before("requestTypeService() && requestType(requestType)")
    public void getRequestTypeValidation(String requestType){
        validateString(requestType);
    }

    public void validateString(String text){
        if(text == null) throw new InvalidRequestException(HttpStatus.BAD_REQUEST, "NULL VALUE PROVIDED");
        if(text.length() <= 1) throw new InvalidRequestException(HttpStatus.BAD_REQUEST, "Invalid text, too short (less than 1): " + text);
        if(text.length() >= 30) throw new InvalidRequestException(HttpStatus.BAD_REQUEST, "Invalid Text, too long (greater than 30): " + text);
    }
}
