package com.revature.thevault.utility.validation;

import com.revature.thevault.presentation.model.request.CreateAccountRequest;
import com.revature.thevault.presentation.model.request.TransferRequest;
import com.revature.thevault.presentation.model.request.UpdateAccountRequest;
import com.revature.thevault.service.exceptions.InvalidAmountException;
import com.revature.thevault.service.exceptions.InvalidRequestException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component("accountValidation")
@Aspect
public class AccountValidation {

    @Pointcut("this(com.revature.thevault.service.classes.AccountService)")
    private void accountService(){}

    @Pointcut("args(createAccountRequest)")
    private void createAccountRequest(CreateAccountRequest createAccountRequest){}

    @Pointcut("args(updateAccountRequest)")
    private void updateAccountRequest(UpdateAccountRequest updateAccountRequest){}

    @Pointcut("args(transferRequest)")
    private void transferRequest(TransferRequest transferRequest){}

    @Pointcut("args(userId)")
    private void userIdArgument(Integer userId){}

    @Pointcut("args(accountId)")
    private void accountIdArgument(Integer accountId){}

    @Before("accountService() && userIdArgument(userId)")
    public void validateUserId(Integer userId){
        validateInteger(userId);
    }

    @Before("accountService() && accountIdArgument(accountId)")
    public void validateAccountId(Integer accountId){
        validateInteger(accountId);
    }

    @Before("accountService() && createAccountRequest(createAccountRequest)")
    public void createAccountRequestValidation(CreateAccountRequest createAccountRequest){
        validateString(createAccountRequest.getAccountType());
        validateInteger(createAccountRequest.getUserId());
    }

    @Before("accountService() && updateAccountRequest(updateAccountRequest)")
    public void updateAccountRequestValidation(UpdateAccountRequest updateAccountRequest){
        validateInteger(updateAccountRequest.getAccountId());
        validateString(updateAccountRequest.getAccountType());
        validateAmount(updateAccountRequest.getAvailableBalance());
        validateAmount(updateAccountRequest.getPendingBalance());
    }

    @Before("accountService() && transferRequest(transferRequest)")
    public void transferRequestValidation(TransferRequest transferRequest){
        validateInteger(transferRequest.getOwnerAccountId());
        validateInteger(transferRequest.getReceiverAccountId());
        validateAmount(transferRequest.getAmount());
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
