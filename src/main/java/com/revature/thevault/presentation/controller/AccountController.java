package com.revature.thevault.presentation.controller;

import com.revature.thevault.presentation.model.request.GetAccountRequestAll;
import com.revature.thevault.presentation.model.request.GetAccountRequestSingle;
import com.revature.thevault.presentation.model.response.builder.GetResponse;
import com.revature.thevault.service.classes.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController("accountController")
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/find")
    public GetResponse getAccount(@RequestBody GetAccountRequestSingle getAccountRequestSingle){
        return accountService.getAccount(getAccountRequestSingle);
    }

    @GetMapping("/users-accounts")
    public GetResponse getAccountList(@RequestBody GetAccountRequestAll getAccountRequestAll){
        return accountService.getAccounts(getAccountRequestAll);
    }

}
