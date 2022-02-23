package com.revature.thevault.presentation.controller;

import com.revature.thevault.presentation.model.request.DepositRequest;
import com.revature.thevault.presentation.model.response.builder.DeleteResponse;
import com.revature.thevault.presentation.model.response.builder.GetResponse;
import com.revature.thevault.presentation.model.response.builder.PostResponse;
import com.revature.thevault.service.classes.DepositService;
import com.revature.thevault.service.exceptions.InvalidAuthorizationError;
import com.revature.thevault.utility.JWTInfo;
import com.revature.thevault.utility.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController("depositController")
@RequestMapping("/deposit")
public class DepositController {

		@Autowired
		private DepositService depositService;

		@ResponseStatus(HttpStatus.CREATED)
		@PostMapping("/create")
		public PostResponse createDeposit(@RequestHeader("Authorization") String token, @RequestBody DepositRequest depositRequest) {
			JWTInfo parsedJWT = JWTUtility.verifyUser(token);
			if(parsedJWT != null) return depositService.createDeposit(depositRequest);
        else throw new InvalidAuthorizationError(HttpStatus.UNAUTHORIZED, "No valid JWT");
		}

		@GetMapping("/detail")
		public GetResponse getByDepositId(@RequestHeader("Authorization") String token, @RequestParam int depositId){
			JWTInfo parsedJWT = JWTUtility.verifyUser(token);
			if(parsedJWT != null) return depositService.findByDepositId(depositId);
        else throw new InvalidAuthorizationError(HttpStatus.UNAUTHORIZED, "No valid JWT");
		}

		@ResponseStatus(HttpStatus.OK)
		@GetMapping("/all/{id}")
		public GetResponse findAllById(@RequestHeader("Authorization") String token, @PathVariable Integer id) {
			JWTInfo parsedJWT = JWTUtility.verifyUser(token);
			if(parsedJWT != null) return depositService.getAllUserDeposits(id);
        else throw new InvalidAuthorizationError(HttpStatus.UNAUTHORIZED, "No valid JWT");
		}

		@ResponseStatus(HttpStatus.OK)
		@GetMapping(path = "/type/{accountId}", produces = MediaType.APPLICATION_JSON_VALUE)
		public GetResponse findByAccountIdAndDepositTypeId(@RequestHeader("Authorization") String token, @RequestParam String depositType, @PathVariable Integer accountId) {
			JWTInfo parsedJWT = JWTUtility.verifyUser(token);
			if(parsedJWT != null) return this.depositService.getAlLUserDepositsOfType(accountId, depositType);
        else throw new InvalidAuthorizationError(HttpStatus.UNAUTHORIZED, "No valid JWT");
		}

		@DeleteMapping("/clear/{accountId}")
		public DeleteResponse deleteAllDeposits(@RequestHeader("Authorization") String token, @PathVariable Integer accountId){
			JWTInfo parsedJWT = JWTUtility.verifyUser(token);
			if(parsedJWT != null) return this.depositService.deleteAllDeposits(accountId);
        else throw new InvalidAuthorizationError(HttpStatus.UNAUTHORIZED, "No valid JWT");
		}
}


