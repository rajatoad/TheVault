package com.revature.thevault.presentation.controller;

import com.revature.thevault.presentation.model.request.*;
import com.revature.thevault.presentation.model.response.builder.DeleteResponse;
import com.revature.thevault.presentation.model.response.builder.GetResponse;
import com.revature.thevault.presentation.model.response.builder.PostResponse;
import com.revature.thevault.presentation.model.response.builder.PutResponse;
import com.revature.thevault.service.classes.AccountService;
import com.revature.thevault.service.exceptions.InvalidAuthorizationError;
import com.revature.thevault.utility.JWTInfo;
import com.revature.thevault.utility.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController("accountController")
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/find", produces = MediaType.APPLICATION_JSON_VALUE)
    public GetResponse getAccount(@RequestHeader("Authorization") String token, @RequestParam int accountId){
        JWTInfo parsedJWT = JWTUtility.verifyUser(token);
        if(parsedJWT != null) return accountService.getAccount(accountId);
        else throw new InvalidAuthorizationError(HttpStatus.UNAUTHORIZED, "No valid JWT");
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/users-accounts", produces = MediaType.APPLICATION_JSON_VALUE)
    public GetResponse getUserAccountList(@RequestHeader("Authorization") String token, @RequestParam int userId){
        JWTInfo parsedJWT = JWTUtility.verifyUser(token);
        if(parsedJWT != null) return accountService.getAccounts(userId);
        else throw new InvalidAuthorizationError(HttpStatus.UNAUTHORIZED, "No valid JWT");
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public PostResponse createAccount(@RequestHeader("Authorization") String token, @RequestBody CreateAccountRequest createAccountRequest){
        JWTInfo parsedJWT = JWTUtility.verifyUser(token);
        if(parsedJWT != null) return accountService.createAccount(createAccountRequest);
        else throw new InvalidAuthorizationError(HttpStatus.UNAUTHORIZED, "No valid JWT");
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(path = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public DeleteResponse deleteAccount(@RequestHeader("Authorization") String token, @RequestParam int accountId){
        JWTInfo parsedJWT = JWTUtility.verifyUser(token);
        if(parsedJWT != null) return accountService.deleteAccount(accountId);
        else throw new InvalidAuthorizationError(HttpStatus.UNAUTHORIZED, "No valid JWT");
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public PutResponse updateAccount(@RequestHeader("Authorization") String token, @RequestBody UpdateAccountRequest updateAccountRequest){
        JWTInfo parsedJWT = JWTUtility.verifyUser(token);
        if(parsedJWT != null) return accountService.updateAccount(updateAccountRequest);
        else throw new InvalidAuthorizationError(HttpStatus.UNAUTHORIZED, "No valid JWT");
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(path = "/transfer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public PutResponse transferToAccount(@RequestHeader("Authorization") String token, @RequestBody TransferRequest transferRequest){
        JWTInfo parsedJWT = JWTUtility.verifyUser(token);
        if(parsedJWT != null) return accountService.transferToAnotherAccount(transferRequest);
        else throw new InvalidAuthorizationError(HttpStatus.UNAUTHORIZED, "No valid JWT");
    }

}
