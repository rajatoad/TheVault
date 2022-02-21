package com.revature.thevault.utility.validation;

import com.revature.thevault.presentation.model.request.WithdrawRequest;
import com.revature.thevault.service.exceptions.InvalidAmountException;
import com.revature.thevault.service.exceptions.InvalidRequestException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component("withdrawValidation")
@Aspect
public class WithdrawValidation {

    @Pointcut("this(com.revature.thevault.service.classes.WithdrawService) &&" +
            "args(withdrawRequest)")
    private void withdrawRequest(WithdrawRequest withdrawRequest){}

    @Before("withdrawRequest(withdrawRequest)")
    public void validateWithdrawRequest(WithdrawRequest withdrawRequest){
        validateInteger(withdrawRequest.getAccountId());
        validateString(withdrawRequest.getRequestType());
        validateString(withdrawRequest.getReference());
        validateAmount(withdrawRequest.getAmount());
    }

    public void validateAmount(Float amount){
        if(amount <= 0) throw new InvalidAmountException(HttpStatus.BAD_REQUEST, "Invalid amount, less than or equal to 0: " + amount);
    }

    public void validateInteger(int number){
        if(number <= 0) throw new InvalidRequestException(HttpStatus.BAD_REQUEST, "Invalid integer, too small (less than 0): " + number);
    }

    public void validateString(String text){
        if(text == null) throw new InvalidRequestException(HttpStatus.BAD_REQUEST, "NULL VALUE PROVIDED");
        if(text.length() <= 1) throw new InvalidRequestException(HttpStatus.BAD_REQUEST, "Invalid text, too short (less than 1): " + text);
        if(text.length() >= 30) throw new InvalidRequestException(HttpStatus.BAD_REQUEST, "Invalid Text, too long (greater than 30): " + text);
    }
}
