package com.revature.thevault.presentation.controller;

import com.revature.thevault.presentation.model.response.builder.GetResponse;
import com.revature.thevault.service.classes.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController("transactionController")
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/history/{accountId}")
    public GetResponse getTransactionHistory(@PathVariable Integer accountId) {
        return transactionService.getTransactionHistory(accountId);
    }

}
