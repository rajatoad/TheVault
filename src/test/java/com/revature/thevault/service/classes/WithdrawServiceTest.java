package com.revature.thevault.service.classes;

import com.revature.thevault.presentation.model.request.WithdrawRequest;
import com.revature.thevault.presentation.model.response.builder.DeleteResponse;
import com.revature.thevault.presentation.model.response.builder.GetResponse;
import com.revature.thevault.presentation.model.response.builder.PostResponse;
import com.revature.thevault.repository.dao.WithdrawRepository;
import com.revature.thevault.repository.entity.*;
import com.revature.thevault.service.dto.WithdrawResponseObject;
import com.revature.thevault.service.exceptions.InvalidAmountException;
import com.revature.thevault.service.exceptions.InvalidRequestException;
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

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class WithdrawServiceTest {

    @Autowired
    private WithdrawService withdrawService;

    @MockBean
    private WithdrawRepository withdrawRepository;

    @MockBean
    private RequestTypeService requestTypeService;

    @MockBean
    private RequestStatusService requestStatusService;

    private int accountId;
    private String reference;
    private float amount;

    private WithdrawEntity storedWithdrawEntity;
    private AccountEntity accountEntity;
    private WithdrawResponseObject withdrawResponseObject;

    private RequestTypeEntity requestType;
    private RequestStatusEntity requestStatusEntity;
    private Date dateStored;

    private LoginCredentialEntity loginCredentialEntity;
    private AccountTypeEntity accountTypeEntity;

    @BeforeAll
    void setup(){
        MockitoAnnotations.openMocks(this);
        accountId = 1;
        reference = "reference";
        amount = 12.22F;
        loginCredentialEntity = new LoginCredentialEntity(1, "username", "password");
        accountTypeEntity = new AccountTypeEntity(1, "Checking");

        requestType = new RequestTypeEntity(1, "Retail");
        requestStatusEntity = new RequestStatusEntity(1, "Pending");
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
        storedWithdrawEntity = new WithdrawEntity(
                1,
                accountEntity,
                requestType,
                requestStatusEntity,
                reference,
                dateStored,
                amount
        );
        withdrawResponseObject = new WithdrawResponseObject(
                storedWithdrawEntity.getPk_withdraw_id(),
                storedWithdrawEntity.getAccountentity().getPk_account_id(),
                storedWithdrawEntity.getRequesttypeentity().getName(),
                storedWithdrawEntity.getRequeststatusentity().getName(),
                storedWithdrawEntity.getReference(),
                storedWithdrawEntity.getDate_withdraw().toLocalDate(),
                storedWithdrawEntity.getAmount()
        );
        Mockito.when(requestTypeService.getRequestTypeByName("Retail")).thenReturn(requestType);
        Mockito.when(requestStatusService.getRequestStatusByName("Pending")).thenReturn(requestStatusEntity);
    }

    @Test
    void createWithdrawal() {
        WithdrawRequest createWithdrawRequest = new WithdrawRequest(
                accountId,
                storedWithdrawEntity.getRequesttypeentity().getName(),
                reference,
                amount
        );

        PostResponse createdWithdrawResponse = PostResponse.builder()
                .success(true)
                        .createdObject(Collections.singletonList(withdrawResponseObject))
                .build();

        WithdrawEntity saveWithdraw = new WithdrawEntity(
                0,
                new AccountEntity(createWithdrawRequest.getAccountId(), new LoginCredentialEntity(), new AccountTypeEntity(), 0, 0),
                requestType,
                requestStatusEntity,
                createWithdrawRequest.getReference(),
                dateStored,
                createWithdrawRequest.getAmount()
        );
        Mockito.when(withdrawRepository.save(saveWithdraw)).thenReturn(storedWithdrawEntity);
        assertEquals(createdWithdrawResponse, withdrawService.createWithdrawal(createWithdrawRequest));
    }

    @ParameterizedTest
    @ValueSource(floats = {0F, -1F, -2123F})
    void createWithdrawInvalidAmountException(float number){
        WithdrawRequest invalidRequest = new WithdrawRequest(
                accountId,
                requestType.getName(),
                reference,
                number
        );
        assertThrows(InvalidAmountException.class, () -> withdrawService.createWithdrawal(invalidRequest));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"asdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasf", "a"})
    void createWithdrawInvalidRequestType(String string){
        WithdrawRequest invalidRequest = new WithdrawRequest(
                accountId,
                string,
                reference,
                1F
        );
        assertThrows(InvalidRequestException.class, () -> withdrawService.createWithdrawal(invalidRequest));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"asdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasf", "a"})
    void createWithdrawInvalidReference(String string){
        WithdrawRequest invalidRequest = new WithdrawRequest(
                accountId,
                requestType.getName(),
                string,
                1F
        );
        assertThrows(InvalidRequestException.class, () -> withdrawService.createWithdrawal(invalidRequest));
    }

    @Test
    void getAllUserWithdrawals() {
        GetResponse getWithdrawsResponse = GetResponse.builder()
                        .success(true)
                                .gotObject(Collections.singletonList(withdrawResponseObject))
                                        .build();
        Mockito.when(withdrawRepository.findByAccountentity(
                new AccountEntity(
                        accountId,
                        new LoginCredentialEntity(),
                        new AccountTypeEntity(),
                        0,
                        0))).thenReturn(Collections.singletonList(storedWithdrawEntity));
        assertEquals(getWithdrawsResponse, withdrawService.getAllUserWithdrawals(accountId));
    }

    @Test
    void getAlLUserWithdrawalsOfType() {
        GetResponse getWithdrawsResponse = GetResponse.builder()
                .success(true)
                .gotObject(Collections.singletonList(withdrawResponseObject))
                .build();

        Mockito.when(withdrawRepository.findByAccountentityAndRequesttypeentity(
                new AccountEntity(
                        accountId,
                        new LoginCredentialEntity(),
                        new AccountTypeEntity(),
                        0,
                        0),
                requestType))
                .thenReturn(Collections.singletonList(storedWithdrawEntity));

        assertEquals(getWithdrawsResponse, withdrawService.getAlLUserWithdrawalsOfType(accountId, requestType.getName()));
    }

    @Test
    void findByWithdrawId() {
        GetResponse getWithdrawsResponse = GetResponse.builder()
                .success(true)
                .gotObject(Collections.singletonList(withdrawResponseObject))
                .build();
        Mockito.when(withdrawRepository.findById(storedWithdrawEntity.getPk_withdraw_id())).thenReturn(Optional.of(storedWithdrawEntity));
        assertEquals(getWithdrawsResponse, withdrawService.findByWithdrawId(storedWithdrawEntity.getPk_withdraw_id()));
    }

    @Test
    void deleteAllWithdraws() {
        DeleteResponse deleteAllWithdrawResponse = DeleteResponse.builder()
                        .success(true)
                                .deletedObject(Collections.EMPTY_LIST)
                                        .build();
        Mockito.doNothing().when(withdrawRepository).deleteByAccountentity(
                new AccountEntity(
                    accountId,
                    new LoginCredentialEntity(),
                    new AccountTypeEntity(),
                    0,
                    0
                ));
        assertEquals(deleteAllWithdrawResponse, withdrawService.deleteAllWithdraws(accountId));
    }
}