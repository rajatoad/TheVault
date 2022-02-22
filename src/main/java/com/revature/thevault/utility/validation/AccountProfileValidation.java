package com.revature.thevault.utility.validation;

import com.revature.thevault.presentation.model.request.AccountProfileRequest;
import com.revature.thevault.presentation.model.request.ProfileCreateRequest;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component("accountProfileValidation")
@Aspect
public class AccountProfileValidation {

    @Pointcut("this(com.revature.thevault.service.classes.AccountProfileService)")
    private void accountProfileService(){}

    @Pointcut("args(accountProfileRequest)")
    private void accountProfileRequest(AccountProfileRequest accountProfileRequest){}

    @Pointcut("args(profileCreateRequest)")
    private void profileCreateRequest(ProfileCreateRequest profileCreateRequest){}

    @Before("accountProfileService() && profileCreateRequest(profileCreateRequest)")
    public void validateProfileCreateRequest(ProfileCreateRequest profileCreateRequest){

    }

    @Before("accountProfileService() && accountProfileRequest(accountProfileRequest)")
    public void validateAccountProfileRequest(AccountProfileRequest accountProfileRequest){

    }
}
