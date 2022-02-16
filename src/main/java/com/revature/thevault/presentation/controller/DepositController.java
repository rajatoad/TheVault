
package com.revature.thevault.presentation.controller;

import com.revature.thevault.presentation.model.request.DepositRequest;
import com.revature.thevault.presentation.model.response.builder.GetResponse;
import com.revature.thevault.presentation.model.response.builder.PostResponse;
import com.revature.thevault.service.classes.DepositService;
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
		public PostResponse createDeposit(@RequestBody DepositRequest depositRequest) {
				return depositService.createDeposit(depositRequest);
		}

		@GetMapping("/detail")
		public GetResponse getByDepositId(@RequestParam int depositId){
			return depositService.findByDepositId(depositId);
		}

		@ResponseStatus(HttpStatus.OK)
		@GetMapping("/all/{id}")
		public GetResponse findAllById(@PathVariable Integer id) {
			return depositService.getAllUserDeposits(id);
		}

		@ResponseStatus(HttpStatus.OK)
		@GetMapping(path = "/type/{accountId}", produces = MediaType.APPLICATION_JSON_VALUE)
		public GetResponse findByAccountIdAndDepositTypeId(@RequestParam String depositType, @PathVariable Integer accountId) {
			return this.depositService.getAlLUserDepositsOfType(accountId, depositType);
		}

}


