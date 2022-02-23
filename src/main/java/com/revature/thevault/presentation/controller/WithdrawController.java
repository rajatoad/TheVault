package com.revature.thevault.presentation.controller;

import com.revature.thevault.presentation.model.request.WithdrawRequest;
import com.revature.thevault.presentation.model.response.builder.DeleteResponse;
import com.revature.thevault.presentation.model.response.builder.GetResponse;
import com.revature.thevault.presentation.model.response.builder.PostResponse;
import com.revature.thevault.service.classes.WithdrawService;
import com.revature.thevault.service.exceptions.InvalidAuthorizationError;
import com.revature.thevault.utility.JWTInfo;
import com.revature.thevault.utility.JWTUtility;
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
    public GetResponse getUsersWithdraws(@RequestHeader("Authorization") String token, @PathVariable Integer accountId){
        JWTInfo parsedJWT = JWTUtility.verifyUser(token);
        if(parsedJWT != null) return withdrawService.getAllUserWithdrawals(accountId);
        else throw new InvalidAuthorizationError(HttpStatus.UNAUTHORIZED, "No valid JWT");
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/generate")
    public PostResponse createWithdrawal(@RequestHeader("Authorization") String token, @RequestBody WithdrawRequest withdrawRequest){
        JWTInfo parsedJWT = JWTUtility.verifyUser(token);
        if(parsedJWT != null) return withdrawService.createWithdrawal(withdrawRequest);
        else throw new InvalidAuthorizationError(HttpStatus.UNAUTHORIZED, "No valid JWT");
    }

    @GetMapping("/detail")
    public GetResponse findByWithdrawId(@RequestHeader("Authorization") String token, @RequestParam int withdrawId){
        JWTInfo parsedJWT = JWTUtility.verifyUser(token);
        if(parsedJWT != null) return withdrawService.findByWithdrawId(withdrawId);
        else throw new InvalidAuthorizationError(HttpStatus.UNAUTHORIZED, "No valid JWT");
    }

    @GetMapping("/type/{requestType}")
    public GetResponse getWithdrawalByType(@RequestHeader("Authorization") String token, @PathVariable String requestType, @RequestParam int accountId){
        JWTInfo parsedJWT = JWTUtility.verifyUser(token);
        if(parsedJWT != null) return withdrawService.getAlLUserWithdrawalsOfType(accountId, requestType);
        else throw new InvalidAuthorizationError(HttpStatus.UNAUTHORIZED, "No valid JWT");
    }

    @DeleteMapping("/clear/{accountId}")
    public DeleteResponse deleteAllWithdraws(@RequestHeader("Authorization") String token, @PathVariable Integer accountId){
        JWTInfo parsedJWT = JWTUtility.verifyUser(token);
        if(parsedJWT != null) return withdrawService.deleteAllWithdraws(accountId);
        else throw new InvalidAuthorizationError(HttpStatus.UNAUTHORIZED, "No valid JWT");
    }
}
