package com.revature.thevault.utility.validation;


import com.revature.thevault.presentation.model.request.LoginRequest;
import com.revature.thevault.service.exceptions.InvalidRequestException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component("loginValidation")
@Aspect
public class LoginValidation {

    @Pointcut("this(com.revature.thevault.service.classes.LoginService)")
    private void loginService(){}

    @Pointcut("this(com.revature.thevault.presentation.controller.LoginController)")
    private void loginController(){}

    @Pointcut("args(loginRequest)")
    private void loginRequest(LoginRequest loginRequest){}

    @Before("loginService() && loginRequest(loginRequest)")
    public void validateLoginRequest(LoginRequest loginRequest){
        validateString(loginRequest.getUsername());
        validateString(loginRequest.getPassword());
    }

    public void validateString(String text){
        if(text == null) throw new InvalidRequestException(HttpStatus.BAD_REQUEST, "NULL VALUE PROVIDED");
        if(text.length() <= 1) throw new InvalidRequestException(HttpStatus.BAD_REQUEST, "Invalid text, too short (less than 1): " + text);
        if(text.length() >= 30) throw new InvalidRequestException(HttpStatus.BAD_REQUEST, "Invalid Text, too long (greater than 30): " + text);
    }
}
