package com.revature.thevault.service.interfaces;

import com.revature.thevault.presentation.model.request.LoginRequest;
import com.revature.thevault.presentation.model.request.ResetPasswordRequest;
import com.revature.thevault.presentation.model.response.LoginResponse;
import com.revature.thevault.presentation.model.response.builder.GetResponse;
import com.revature.thevault.presentation.model.response.builder.PostResponse;
import com.revature.thevault.repository.entity.LoginCredentialEntity;
import com.revature.thevault.service.exceptions.InvalidInputException;

public interface LoginServiceInterface {
    LoginResponse checkLogin(LoginRequest loginRequest);

    GetResponse getLoginCredentialFromLogin(LoginRequest loginRequest);

    PostResponse createNewLogin(LoginCredentialEntity newLoginRequest) throws InvalidInputException;

    PostResponse resetPassword(ResetPasswordRequest resetPasswordRequest);

    LoginCredentialEntity findUserByUserId(int userId);
}
