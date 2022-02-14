package com.revature.thevault.presentation.controller;

import com.revature.thevault.presentation.model.request.LoginRequest;
import com.revature.thevault.presentation.model.response.LoginResponse;
import com.revature.thevault.presentation.model.response.ProfileResponse;
import com.revature.thevault.repository.entity.NewLoginCredentialsRequest;
import com.revature.thevault.service.classes.AccountProfileService;
import com.revature.thevault.service.classes.LoginService;
import com.revature.thevault.service.interfaces.AccountProfileInterface;
import lombok.NonNull;
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

//    @ResponseStatus(HttpStatus.CREATED)
//    @PostMapping("/new")
//    public ProfileResponse newLogin(@NonNull @RequestBody NewLoginCredentialsRequest newUser){
//        return profileService.newUserProfile(loginService.newAccount(newUser), newUser.getEmail());
//    }


}
