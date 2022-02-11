package com.revature.thevault.service.interfaces;

import com.revature.thevault.presentation.model.request.LoginRequest;
import com.revature.thevault.presentation.model.response.LoginResponse;
import com.revature.thevault.repository.entity.LoginCredentialEntity;
import com.revature.thevault.presentation.model.request.NewLoginCredentialsRequest;
import com.revature.thevault.service.exceptions.InvalidInputException;

public interface LoginServiceInterface {
    LoginResponse checkLogin(LoginRequest loginRequest);

    LoginCredentialEntity getUserCredentialFromLogin(LoginRequest loginRequest);

    LoginCredentialEntity newAccount(NewLoginCredentialsRequest newUserAccountRequest) throws InvalidInputException;
}
