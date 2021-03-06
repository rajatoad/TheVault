package com.revature.thevault.presentation.controller;

import com.revature.thevault.presentation.model.request.LoginRequest;
import com.revature.thevault.presentation.model.request.NewLoginCredentialsRequest;
import com.revature.thevault.presentation.model.request.ResetPasswordRequest;
import com.revature.thevault.presentation.model.response.LoginResponse;
import com.revature.thevault.presentation.model.response.builder.PostResponse;

import com.revature.thevault.presentation.model.response.builder.PutResponse;
import com.revature.thevault.service.classes.AccountProfileService;
import com.revature.thevault.service.classes.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController("loginController")
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private AccountProfileService accountProfileService;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping("/check")
    public LoginResponse checkLogin(@RequestBody LoginRequest loginRequest){
        return loginService.checkLogin(loginRequest);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public PostResponse newLogin(@RequestBody NewLoginCredentialsRequest newLoginRequest){
        return loginService.createNewLogin(newLoginRequest);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/validate")
    public PostResponse findLoginCredential(@RequestBody LoginRequest loginRequest){
        return loginService.getLoginCredentialFromLogin(loginRequest);
    }

    @PutMapping ("/reset-password")
    public PutResponse resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest){
        return loginService.resetPassword(resetPasswordRequest);
    }
}
