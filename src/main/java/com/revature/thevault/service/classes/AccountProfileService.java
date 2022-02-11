package com.revature.thevault.service.classes;

import com.revature.thevault.presentation.model.request.AccountProfileRequest;
import com.revature.thevault.presentation.model.request.ProfileCreateRequest;
import com.revature.thevault.presentation.model.response.AccountProfileResponse;
import com.revature.thevault.repository.dao.AccountProfileRepository;
import com.revature.thevault.repository.entity.AccountProfileEntity;
import com.revature.thevault.repository.entity.LoginCredentialEntity;
import com.revature.thevault.service.interfaces.AccountProfileInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("accountProfileService")
public class AccountProfileService implements AccountProfileInterface {

    @Autowired
    private AccountProfileRepository accountProfileRepository;

    @Override
    public AccountProfileResponse getProfile(AccountProfileRequest accountProfileRequest) {
        try {
            LoginCredentialEntity loginCredentialEntity = new LoginCredentialEntity(accountProfileRequest.getUserId(), "", "");
            AccountProfileEntity accountProfileEntity = accountProfileRepository.findByLogincredential(loginCredentialEntity);
            return new AccountProfileResponse(true, accountProfileEntity);
        }catch(Exception e){
            return failResponse();
        }
    }

    private AccountProfileResponse failResponse() {
        return new AccountProfileResponse(false, null);
    }

    @Override
    public AccountProfileResponse createProfile(ProfileCreateRequest profileCreateRequest) {
        return null;
    }
}
