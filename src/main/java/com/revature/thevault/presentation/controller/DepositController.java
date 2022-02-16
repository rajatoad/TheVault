
package com.revature.thevault.presentation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.thevault.presentation.model.request.DepositRequest;
import com.revature.thevault.presentation.model.response.DepositResponse;
import com.revature.thevault.presentation.model.response.builder.GetResponse;
import com.revature.thevault.repository.entity.DepositEntity;
import com.revature.thevault.service.classes.DepositService;

	@CrossOrigin("*")
	@RestController("depositController")
	@RequestMapping("/deposit")
	public class DepositController {
		
		  @Autowired
		    private DepositService depositService;
		  	
//		  @ResponseStatus(HttpStatus.CREATED)
//		    @PostMapping("/create")
//		    public DepositResponse createDeposit(@RequestBody DepositRequest depositRequest) {
//		        return depositService.createDeposit(depositRequest);
//		    }
		  	
//		  @ResponseStatus(HttpStatus.OK)
//		    @GetMapping("/create")
//			public GetResponse findAllById(@RequestParam int accountId) {
//				List<DepositEntity> allUserDeposits = findAllById(accountId);
//				return allUserDeposits;
//			}

//		  @ResponseStatus(HttpStatus.OK)
//		    @GetMapping("/alluserdeposits", produces = MediaType.APPLICATION_JSON_VALUE)
//			public GetResponse findByAccountIdAndDepositTypeId(@RequestParam int accountId, int depositTypeId) {
//				return this.depositService.getAlLUserDepositsOfType( accountId, depositTypeId);
//			}
//		  @ResponseStatus(HttpStatus.OK)
//		    @GetMapping("/alldeposits")
//			public GetResponse getAllDeposits() {
//			
//				return depositService.findAll();
//			}
//	
//		  @ResponseStatus(HttpStatus.OK)
//		    @GetMapping("/bytype")
//			public GetResponse getAllDepositsOfType(int depositTypeId) {
//				
//				return this.depositService.findAllById(depositTypeId);
//				
//			}
	}


