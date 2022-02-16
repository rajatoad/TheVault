package com.revature.thevault.service.dto;


import com.revature.thevault.repository.entity.NewLoginCredentialsRequest;

public class LoginCredentialResponse extends NewLoginCredentialsRequest {

    public LoginCredentialResponse(String username, String password){
        super(username, password);

    }

}
