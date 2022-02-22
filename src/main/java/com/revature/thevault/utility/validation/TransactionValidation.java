package com.revature.thevault.utility.validation;

import com.revature.thevault.service.exceptions.InvalidAccountIdException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component("transactionValidation")
@Aspect
public class TransactionValidation {
    @Pointcut("this(com.revature.thevault.service.classes.TransactionService)")
    private void transactionService(){}

    @Pointcut("args(accountId)")
    private void integerRequest(Integer accountId){}

    @Before("integerRequest(accountId) && transactionService()")
    public void validateIntegerInput(Integer accountId){
        if(accountId <= 0) throw new InvalidAccountIdException(HttpStatus.BAD_REQUEST, "Invalid account Id Provided: " + accountId);
    }
}
