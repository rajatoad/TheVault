
package com.revature.thevault.presentation.controller;

import com.revature.thevault.presentation.model.request.AccountProfileRequest;
import com.revature.thevault.presentation.model.request.ProfileCreateRequest;
import com.revature.thevault.presentation.model.response.AccountProfileResponse;
import com.revature.thevault.presentation.model.response.builder.DeleteResponse;
import com.revature.thevault.presentation.model.response.builder.GetResponse;
import com.revature.thevault.presentation.model.response.builder.PostResponse;
import com.revature.thevault.repository.entity.AccountProfileEntity;
import com.revature.thevault.repository.entity.LoginCredentialEntity;
import com.revature.thevault.service.classes.AccountProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController("accountProfileController")
@RequestMapping("/profile")
public class AccountProfileController {

    @Autowired
    private AccountProfileService accountProfileService;

    @PostMapping("/create")
    public PostResponse createProfile(@RequestBody ProfileCreateRequest profileCreateRequest){
        return accountProfileService.createProfile(profileCreateRequest);
    }

    @GetMapping("/get/{id}")
    public GetResponse getProfile(@PathVariable int id){
        return accountProfileService.getProfile(new AccountProfileRequest(id));
    }

    @PostMapping("/delete")
    public DeleteResponse deleteProfile(@RequestBody AccountProfileRequest accountProfileRequest){
        return accountProfileService.deleteProfile(accountProfileRequest);
    }

    @PostMapping("/update")
    public PostResponse updateProfile(@RequestBody ProfileCreateRequest profileCreateRequest){
        return accountProfileService.updateProfile(profileCreateRequest);
    }
}