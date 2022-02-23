package com.revature.thevault.presentation.controller;

import com.revature.thevault.presentation.model.response.builder.GetResponse;
import com.revature.thevault.service.classes.TransactionService;
import com.revature.thevault.service.exceptions.InvalidAuthorizationError;
import com.revature.thevault.utility.JWTInfo;
import com.revature.thevault.utility.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController("transactionController")
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/history/{accountId}")
    public GetResponse getTransactionHistory(@RequestHeader("Authorization") String token, @PathVariable Integer accountId) {
        JWTInfo parsedJWT = JWTUtility.verifyUser(token);
        if(parsedJWT != null) return transactionService.getTransactionHistory(accountId);
        else throw new InvalidAuthorizationError(HttpStatus.UNAUTHORIZED, "No valid JWT");
    }

}
