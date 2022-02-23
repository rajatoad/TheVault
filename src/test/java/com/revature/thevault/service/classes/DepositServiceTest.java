package com.revature.thevault.service.classes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import com.revature.thevault.presentation.model.request.DepositRequest;
import com.revature.thevault.presentation.model.response.builder.GetResponse;
import com.revature.thevault.presentation.model.response.builder.PostResponse;
import com.revature.thevault.repository.dao.DepositRepository;
import com.revature.thevault.repository.entity.AccountEntity;
import com.revature.thevault.repository.entity.AccountTypeEntity;
import com.revature.thevault.repository.entity.DepositEntity;
import com.revature.thevault.repository.entity.DepositTypeEntity;
import com.revature.thevault.repository.entity.LoginCredentialEntity;
import com.revature.thevault.service.dto.DepositResponseObject;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)

public class DepositServiceTest {
	@Autowired
	private DepositService depositService;

	@MockBean
	private DepositRepository depositRepository;

	@MockBean
	private DepositTypeService depositTypeService;
	
	@MockBean
	private AccountTypeService accountTypeService;
	
	@MockBean
	private DepositRequest depositRequest;

	private int userId;
	private int depositId;
	private int accountId;
	private float amount;
	private String reference;
	private Date depositDate;
	private DepositEntity storedDeposit;
	private Optional<DepositEntity> optionalDeposit;
	private AccountEntity storedAccount;
	private AccountEntity sampleAccount;
	private List<String> depositType;
	private DepositResponseObject storedDepositResponseObject;


	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
		userId = 1;
		depositId = 1;
		accountId = 1;
		reference = "work";
		amount = 100;
		depositDate =  Date.valueOf(LocalDate.now());
		depositType = new ArrayList<>();
		depositType.add("Cash");
		depositType.add("Cheque");
		depositType.add("Direct Deposit");
		AccountTypeEntity accountTypeEntity = new AccountTypeEntity(1, "Checking");
		LoginCredentialEntity primaryLogin = new LoginCredentialEntity(userId, "username", "password");
		DepositTypeEntity depositTypeEntity = new DepositTypeEntity(1, "Cash");
		storedAccount = new AccountEntity(accountId, primaryLogin, accountTypeEntity, 100L, 120L);
		sampleAccount = new AccountEntity(1, new LoginCredentialEntity(userId, "username", "password"), new AccountTypeEntity(1, "checking"), 0, 0);
		storedDeposit = new DepositEntity(depositId,sampleAccount,depositTypeEntity,reference,depositDate,amount);
		optionalDeposit = Optional.of(storedDeposit);
		//storedDeposit = new DepositEntity(depositId,storedAccount,depositTypeEntity,reference,depositDate,amount);	
		storedDepositResponseObject = new DepositResponseObject(
				storedDeposit.getPk_deposit_id(),
				storedDeposit.getAccountentity().getPk_account_id(),
				storedDeposit.getDeposittypeentity().getName(),
				storedDeposit.getReference(),
				storedDeposit.getDate_deposit().toLocalDate(),
				storedDeposit.getAmount()
				);
		Mockito.when(depositRepository.findById(accountId)).thenReturn(optionalDeposit);
		Mockito.when(depositTypeService.findDepositTypeEntityByName(depositType.get(0))).thenReturn(new DepositTypeEntity(1, depositType.get(0)));
        Mockito.when(depositTypeService.findDepositTypeEntityByName(depositType.get(1))).thenReturn(new DepositTypeEntity(2, depositType.get(1)));
        //Mockito.when(accountRepository.findByLogincredentials(new LoginCredentialEntity(userId, "", ""))).thenReturn(Collections.singletonList(storedAccount));
	}
//	- Class: class com.revature.thevault.service.classes.DepositService
//	Method: createDeposit
//	Arguments: [DepositRequest(depositType=Cash, accountId=1, reference=work, amount=100.0)]
//	07:59 ERROR - CONTROLLER CLASS: class com.revature.thevault.service.classes.DepositService
	@Test
	void createDeposit() {
		DepositRequest successfulDepositRequest = new DepositRequest(depositType.get(0), 1, reference, 100);
		DepositEntity makingDeposit = new DepositEntity( 0,
                new AccountEntity(storedDeposit.getAccountentity().getPk_account_id(), new LoginCredentialEntity(), new AccountTypeEntity(), 0, 0),
				 // new AccountEntity(storedDeposit.getAccountentity().getPk_account_id(), new LoginCredentialEntity(userId,"username","password"), new AccountTypeEntity(1, "Checking"), 0, 0),
              storedDeposit.getDeposittypeentity(),
               storedDeposit.getReference(),
               Date.valueOf(LocalDate.now()),
                storedDeposit.getAmount()
	
       );
		    
		Mockito.when(depositRepository.save(makingDeposit)).thenReturn(storedDeposit);


		PostResponse successfulDepositResponse = PostResponse.builder().success(true)
				.createdObject(Collections.singletonList(storedDepositResponseObject)).build();
		assertEquals(successfulDepositResponse, depositService.createDeposit(successfulDepositRequest));
	}
	
	
	//Returning Empty Object
//	@Test
//    void getAllUserDeposits() {
//        GetResponse goodGetResponse = GetResponse.builder()
//                        .success(true)
//                                .gotObject(Collections.singletonList(storedDepositResponseObject))
//                                        .build();
//        assertEquals(goodGetResponse, depositService.getAllUserDeposits(storedDeposit.getAccountentity().getPk_account_id()));
//    }
//	//Make a request for deposits
//	@Test
//	void getAlLUserDepositsOfType() {
//        GetResponse goodGetResponse = GetResponse.builder()
//                .success(true)
//                        .gotObject(Collections.singletonList(storedDepositResponseObject))
//                                .build();
//assertEquals(goodGetResponse, depositService.getAllUserDeposits(storedDeposit.getAccountentity().getPk_account_id()));
//}
}
