package com.revature.thevault.service.interfaces;

import com.revature.thevault.presentation.model.request.LoginRequest;
import com.revature.thevault.presentation.model.response.LoginResponse;

public interface LoginServiceInterface {
    LoginResponse checkLogin(LoginRequest loginRequest);
}
