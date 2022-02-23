package com.revature.thevault.presentation.controller;

import com.revature.thevault.presentation.model.request.AccountProfileRequest;
import com.revature.thevault.presentation.model.request.ProfileCreateRequest;
import com.revature.thevault.presentation.model.request.UpdateProfileRequest;
import com.revature.thevault.presentation.model.response.AccountProfileResponse;
import com.revature.thevault.presentation.model.response.builder.DeleteResponse;
import com.revature.thevault.presentation.model.response.builder.GetResponse;
import com.revature.thevault.presentation.model.response.builder.PostResponse;
import com.revature.thevault.presentation.model.response.builder.PutResponse;
import com.revature.thevault.repository.entity.AccountProfileEntity;
import com.revature.thevault.repository.entity.LoginCredentialEntity;
import com.revature.thevault.service.classes.AccountProfileService;
import com.revature.thevault.service.exceptions.InvalidAuthorizationError;
import com.revature.thevault.utility.JWTInfo;
import com.revature.thevault.utility.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController("accountProfileController")
@RequestMapping("/profile")
public class AccountProfileController {

    @Autowired
    private AccountProfileService accountProfileService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public PostResponse createProfile(@RequestBody ProfileCreateRequest profileCreateRequest){
        return accountProfileService.createProfile(profileCreateRequest);
    }

    @GetMapping("/get/{id}")
    public GetResponse getProfile(@RequestHeader("Authorization") String token, @PathVariable int id){
        JWTInfo parsedJWT = JWTUtility.verifyUser(token);
        if(parsedJWT != null) return accountProfileService.getProfile(new AccountProfileRequest(id));
        else throw new InvalidAuthorizationError(HttpStatus.UNAUTHORIZED, "No valid JWT");
    }

    //remove, this is not necessary to the application
    @DeleteMapping("/delete")
    public DeleteResponse deleteProfile(@RequestHeader("Authorization") String token, @RequestBody AccountProfileRequest accountProfileRequest){
        JWTInfo parsedJWT = JWTUtility.verifyUser(token);
        if(parsedJWT != null) return accountProfileService.deleteProfile(accountProfileRequest);
        else throw new InvalidAuthorizationError(HttpStatus.UNAUTHORIZED, "No valid JWT");
    }

    @PutMapping("/update")
    public PutResponse updateProfile(@RequestHeader("Authorization") String token, @RequestBody UpdateProfileRequest updateProfileRequest){
        JWTInfo parsedJWT = JWTUtility.verifyUser(token);
        if(parsedJWT != null) return accountProfileService.updateProfile(updateProfileRequest);
        else throw new InvalidAuthorizationError(HttpStatus.UNAUTHORIZED, "No valid JWT");
    }
}
