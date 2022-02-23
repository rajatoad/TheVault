package com.revature.thevault.utility.validation;

import com.revature.thevault.service.exceptions.InvalidRequestException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component("depositTypeValidation")
@Aspect
public class DepositTypeValidation {

    @Pointcut("this(com.revature.thevault.service.classes.DepositTypeService)")
    private void depositTypeService(){}

    @Pointcut("args(name)")
    private void depositTypeName(String name){}

    @Before("depositTypeService() && depositTypeName(name)")
    public void getDepositTypeByNameValidation(String name){
        validateString(name);
    }

    public void validateString(String text){
        if(text == null) throw new InvalidRequestException(HttpStatus.BAD_REQUEST, "NULL VALUE PROVIDED");
        if(text.length() <= 1) throw new InvalidRequestException(HttpStatus.BAD_REQUEST, "Invalid text, too short (less than 1): " + text);
        if(text.length() >= 30) throw new InvalidRequestException(HttpStatus.BAD_REQUEST, "Invalid Text, too long (greater than 30): " + text);
    }

}
