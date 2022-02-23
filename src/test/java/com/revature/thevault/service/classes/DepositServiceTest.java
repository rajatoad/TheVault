package com.revature.thevault.service.classes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import com.revature.thevault.presentation.model.request.DepositRequest;
import com.revature.thevault.presentation.model.request.WithdrawRequest;
import com.revature.thevault.presentation.model.response.builder.DeleteResponse;
import com.revature.thevault.presentation.model.response.builder.GetResponse;
import com.revature.thevault.presentation.model.response.builder.PostResponse;
import com.revature.thevault.repository.dao.DepositRepository;
import com.revature.thevault.repository.entity.AccountEntity;
import com.revature.thevault.repository.entity.AccountTypeEntity;
import com.revature.thevault.repository.entity.DepositEntity;
import com.revature.thevault.repository.entity.DepositTypeEntity;
import com.revature.thevault.repository.entity.LoginCredentialEntity;
import com.revature.thevault.service.dto.DepositResponseObject;
import com.revature.thevault.service.exceptions.InvalidAccountIdException;
import com.revature.thevault.service.exceptions.InvalidAmountException;
import com.revature.thevault.service.exceptions.InvalidDepositIdException;
import com.revature.thevault.service.exceptions.InvalidRequestException;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DepositServiceTest {
@Autowired
private DepositService depositService;

@MockBean
private DepositRepository depositRepository;

@MockBean
private DepositTypeService depositTypeService;
private int userId;
private int depositId;
private int badDepositId;
private int accountId;
private float amount;
private String reference;

private List<String> depositType;
    private DepositEntity storedDepositEntity;
    private Optional<DepositEntity> optionalDeposit;
    private AccountEntity accountEntity;
    private DepositResponseObject depositResponseObject;

    private Date dateStored;

    private LoginCredentialEntity loginCredentialEntity;
    private DepositTypeEntity depositTypeEntity;
    private AccountTypeEntity accountTypeEntity;
    @BeforeAll
    void setup(){
        MockitoAnnotations.openMocks(this);
        accountId = 1;
        reference = "reference";
        amount = 12.22F;
        loginCredentialEntity = new LoginCredentialEntity(1, "username", "password");
        accountTypeEntity = new AccountTypeEntity(1, "Checking");
        depositTypeEntity = new DepositTypeEntity(1, "Cash");
        depositType = new ArrayList<>();
depositType.add("Cash");
depositType.add("Cheque");
depositType.add("Direct Deposit");
badDepositId = -1;
    }
        @BeforeEach
        void setupBeforeEach(){
            dateStored = Date.valueOf(LocalDate.now());
            accountEntity = new AccountEntity(
                    accountId,
                    loginCredentialEntity,
                    accountTypeEntity,
                    100F,
                    100F
            );
           
            storedDepositEntity = new DepositEntity(
                    1,
                    accountEntity,
                    depositTypeEntity,
                    reference,
                    dateStored,
                    amount
            );
            optionalDeposit = Optional.of(storedDepositEntity);
            depositResponseObject = new DepositResponseObject(
            storedDepositEntity.getPk_deposit_id(),
                    storedDepositEntity.getAccountentity().getPk_account_id(),
                    storedDepositEntity.getDeposittypeentity().getName(),
                    storedDepositEntity.getReference(),
                    storedDepositEntity.getDate_deposit().toLocalDate(),
                    storedDepositEntity.getAmount()
            );

            Mockito.when(depositTypeService.findDepositTypeEntityByName("cash")).thenReturn(depositTypeEntity);
            Mockito.when(depositRepository.findById(depositId)).thenReturn(optionalDeposit);
           
        }

        @Test
        void createDeposit() {
           DepositRequest createDepositRequest = new DepositRequest(
                   storedDepositEntity.getDeposittypeentity().getName(),
                   storedDepositEntity.getAccountentity().getPk_account_id(),
                   storedDepositEntity.getReference(),
                   storedDepositEntity.getAmount()
            );

            PostResponse createDepositResponse = PostResponse.builder()
                    .success(true)
                    .createdObject(Collections.singletonList(depositResponseObject))
                    .build();

            DepositEntity saveDeposit = new DepositEntity(
                    0,
                    new AccountEntity(createDepositRequest.getAccountId(), new LoginCredentialEntity(), new AccountTypeEntity(), 0, 0),
                    depositTypeEntity,
                    createDepositRequest.getReference(),
                    Date.valueOf(LocalDate.now()),
                    createDepositRequest.getAmount()
            );
            Mockito.when(depositRepository.save(saveDeposit)).thenReturn(storedDepositEntity);
            Mockito.when(depositTypeService.findDepositTypeEntityByName(storedDepositEntity.getDeposittypeentity().getName())).thenReturn(storedDepositEntity.getDeposittypeentity());

            assertEquals(createDepositResponse, depositService.createDeposit(createDepositRequest));
        }

        @Test
        void getAllUserDeposits() {
            GetResponse getDepositsResponse = GetResponse.builder()
                            .success(true)
                                    .gotObject(Collections.singletonList(depositResponseObject))
                                            .build();
            Mockito.when(depositRepository.findByAccountentity(
                    new AccountEntity(
                            accountId,
                            new LoginCredentialEntity(),
                            new AccountTypeEntity(),
                            0,
                            0))).thenReturn(Collections.singletonList(storedDepositEntity));
            assertEquals(getDepositsResponse, depositService.getAllUserDeposits(accountId));
        }

        @Test
        void getAlLUserDepositsOfType() {
            GetResponse getDepositResponse = GetResponse.builder()
                    .success(true)
                    .gotObject(Collections.singletonList(depositResponseObject))
                    .build();

            Mockito.when(depositRepository.findByAccountentityAndDeposittypeentity(
                    new AccountEntity(accountId,
                    new LoginCredentialEntity(),
                    new AccountTypeEntity(),
                0,
                0),
                    depositTypeEntity)).thenReturn(Collections.singletonList(storedDepositEntity));

            Mockito.when(depositTypeService.findDepositTypeEntityByName(depositTypeEntity.getName())).thenReturn(depositTypeEntity);

            assertEquals(getDepositResponse, depositService.getAlLUserDepositsOfType(accountId, depositTypeEntity.getName()));
        }

        @Test
        void deleteAllDeposits() {
            DeleteResponse deleteAllDepositResponse = DeleteResponse.builder()
                            .success(true)
                                    .deletedObject(Collections.EMPTY_LIST)
                                            .build();
            Mockito.doNothing().when(depositRepository).deleteByAccountentity(
                    new AccountEntity(
                        accountId,
                        new LoginCredentialEntity(),
                        new AccountTypeEntity(),
                        0,
                        0
                    ));
            assertEquals(deleteAllDepositResponse, depositService.deleteAllDeposits(accountId));
 
       }
       
        @Test
        void findByDepositId() {
            GetResponse getDepositResponse = GetResponse.builder()
                    .success(true)
                    .gotObject(Collections.singletonList(depositResponseObject))
                    .build();
            Mockito.when(depositRepository.findById(storedDepositEntity.getPk_deposit_id())).thenReturn(Optional.of(storedDepositEntity));
            assertEquals(getDepositResponse, depositService.findByDepositId(storedDepositEntity.getPk_deposit_id()));
        }
        @Test
        void findByDepositIdInvalidDepositId(){
            assertThrows(InvalidRequestException.class, () -> depositService.findByDepositId(badDepositId));
           
        }
        @ParameterizedTest
        @ValueSource(floats = {0F, -1F, -2123F})
        void createDepositInvalidAmountException(float number){
            DepositRequest invalidRequest = new DepositRequest(
                   
             storedDepositEntity.getDeposittypeentity().getName(),
                      //accountId,
                      storedDepositEntity.getAccountentity().getPk_account_id(),
                      reference,
                      number
            );
            assertThrows(InvalidAmountException.class, () -> depositService.createDeposit(invalidRequest));
        }
       
        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = {"asdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasf", "a"})
        void createInvalidDepositType(String string){
           DepositRequest invalidRequest = new DepositRequest(
          string,
          storedDepositEntity.getAccountentity().getPk_account_id(),
          reference,
                    1F
            );
           assertThrows(InvalidRequestException.class, () -> depositService.createDeposit(invalidRequest));
        }

        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = {"asdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasf", "a"})
        void createDepositInvalidReference(String string){
            DepositRequest invalidRequest = new DepositRequest(
           
            depositTypeEntity.getName(),
                     storedDepositEntity.getAccountentity().getPk_account_id(),
                     string,
                     amount
            );
            assertThrows(InvalidRequestException.class, () -> depositService.createDeposit(invalidRequest));
        }
}
