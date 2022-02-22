package com.revature.thevault.presentation.controller;

import com.revature.thevault.presentation.model.request.WithdrawRequest;
import com.revature.thevault.presentation.model.response.builder.DeleteResponse;
import com.revature.thevault.presentation.model.response.builder.GetResponse;
import com.revature.thevault.presentation.model.response.builder.PostResponse;
import com.revature.thevault.service.classes.WithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController("withdrawController")
@RequestMapping("/withdraw")
public class WithdrawController {

    @Autowired
    private WithdrawService withdrawService;

    @GetMapping("/all/{accountId}")
    public GetResponse getUsersWithdraws(@PathVariable Integer accountId){
        return withdrawService.getAllUserWithdrawals(accountId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/generate")
    public PostResponse createWithdrawal(@RequestBody WithdrawRequest withdrawRequest){
        return withdrawService.createWithdrawal(withdrawRequest);
    }

    @GetMapping("/detail")
    public GetResponse findByWithdrawId(@RequestParam int withdrawId){
        return withdrawService.findByWithdrawId(withdrawId);
    }

    @GetMapping("/type/{requestType}")
    public GetResponse getWithdrawalByType(@PathVariable String requestType, @RequestParam int accountId){
        return withdrawService.getAlLUserWithdrawalsOfType(accountId, requestType);
    }

    @DeleteMapping("/clear/{accountId}")
    public DeleteResponse deleteAllWithdraws(@PathVariable Integer accountId){
        return withdrawService.deleteAllWithdraws(accountId);
    }
}
