//package com.revature.thevault.presentation.controller;
//
//import com.revature.thevault.presentation.model.request.ProfileCreateRequest;
//import com.revature.thevault.presentation.model.response.AccountProfileResponse;
//import com.revature.thevault.service.classes.AccountProfileService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@CrossOrigin("*")
//@RestController("accountProfileController")
//@RequestMapping("/profile")
//public class AccountProfileController {
//    @Autowired
//    private AccountProfileService accountProfileService;
//
//    @PostMapping("/create")
//    public AccountProfileResponse createProfile(@RequestBody ProfileCreateRequest profileCreateRequest){
//        return accountProfileService.createProfile(profileCreateRequest);
//    }
//}
