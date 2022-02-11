package com.revature.thevault.service.classes;

import com.revature.thevault.presentation.model.request.CreateAccountRequest;
import com.revature.thevault.presentation.model.request.DeleteAccountRequest;
import com.revature.thevault.presentation.model.request.TransferRequest;
import com.revature.thevault.presentation.model.request.UpdateBalanceRequest;
import com.revature.thevault.presentation.model.response.AccountResponse;
import com.revature.thevault.presentation.model.response.GenericResponse;
import com.revature.thevault.repository.dao.AccountRepository;
import com.revature.thevault.repository.entity.AccountEntity;
import com.revature.thevault.repository.entity.AccountTypeEntity;
import com.revature.thevault.repository.entity.LoginCredentialEntity;
import com.revature.thevault.utility.enums.ResponseType;
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

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @MockBean
    private AccountRepository accountRepository;

    private AccountResponse successfulAccountResponse;
    private AccountResponse failedAccountResponse;

    private AccountEntity storedAccount;

    private int userId;
    private int accountId;
    private List<String> accountType;

    private int otherAccountId;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        userId = 1;
        accountId = 1;
        otherAccountId = 2;
        accountType = new ArrayList<>();
        accountType.add("Checking");
        accountType.add("Savings");

        LoginCredentialEntity primaryLogin = new LoginCredentialEntity(userId, "username", "password");
        AccountTypeEntity accountTypeEntity = new AccountTypeEntity(1, "Checking");

        storedAccount = new AccountEntity(accountId, primaryLogin, accountTypeEntity, 100, 120);
        //Optional<AccountEntity> optionalAccount = Optional.of(storedAccount);
        Mockito.when(accountRepository.getById(accountId)).thenReturn(storedAccount);
    }

    @Test
    void createAccount() {
        CreateAccountRequest goodAccountCreateRequest = new CreateAccountRequest(userId, accountType.get(0));
        AccountEntity creatingAccount = new AccountEntity();
        AccountEntity createdAccount = new AccountEntity();
        Mockito.when(accountRepository.save(creatingAccount)).thenReturn(createdAccount);
        assertEquals(successfulAccountResponse, accountService.createAccount(goodAccountCreateRequest));
    }

    @Test
    void createAccountInvalidUserIdRequest(){
        CreateAccountRequest badAccountCreateRequest = new CreateAccountRequest(-1, accountType.get(0));
        GenericResponse failResponse = new GenericResponse.GenericResponseBuilder(false)
                .requestType(ResponseType.FAIL)
                        .message("Failed to create Account")
                                .build();
        AccountEntity badCreatingAccount = new AccountEntity();
        Mockito.when(accountRepository.save(badCreatingAccount)).thenReturn(null);
        assertEquals(failResponse, accountService.createAccount(badAccountCreateRequest));
    }

    @Test
    void createAccountInvalidAccountTypeRequest(){
        CreateAccountRequest badAccountCreateRequest = new CreateAccountRequest(userId, "lol");
        GenericResponse failResponse = new GenericResponse.GenericResponseBuilder(false)
                .requestType(ResponseType.FAIL)
                .message("Failed to create Account")
                .build();
        AccountEntity badCreatingAccount = new AccountEntity();
        Mockito.when(accountRepository.save(badCreatingAccount)).thenReturn(null);
        assertEquals(failResponse, accountService.createAccount(badAccountCreateRequest));
    }

    @Test
    void deleteAccount() {
        DeleteAccountRequest goodDeleteAccountRequest = new DeleteAccountRequest(storedAccount.getPkaccountid());
        GenericResponse successfulDeleteAccountResponse = new GenericResponse.GenericResponseBuilder(true)
                .requestType(ResponseType.DELETE)
                        .message("Successful Account Deletion: " + storedAccount.getPkaccountid())
                                .build();
        assertEquals(successfulDeleteAccountResponse, accountService.deleteAccount(goodDeleteAccountRequest));
    }

    @Test
    void deleteAccountInvalidAccountIdUserNotFound() {
        DeleteAccountRequest badAccountDeleteRequest = new DeleteAccountRequest(-1);
        GenericResponse failedResponse = new GenericResponse.GenericResponseBuilder(false)
                .requestType(ResponseType.FAIL)
                .message("Failed to delete account: Entity Not Found")
                .build();
        Mockito.when(accountRepository.getById(badAccountDeleteRequest.getAccountId())).thenThrow(EntityNotFoundException.class);
        assertEquals(failedResponse, accountService.deleteAccount(badAccountDeleteRequest));
    }


    @Test
    void getAccounts() {
        // find by user id test
    }

    @Test
    void getAccountsInvalidUserId() {
        // find by invalid user Id test
    }

    @Test
    void updateAccountAvailableBalance() {
        //update user account balance
        UpdateBalanceRequest updateBalanceRequest = new UpdateBalanceRequest(accountId, 10);


        GenericResponse updatedBalanceResponse = new GenericResponse.GenericResponseBuilder(true)
                .requestType(ResponseType.UPDATE)
                        .message("Successfully updated users available balance: " + updateBalanceRequest.getAmount())
                                .build();

        AccountEntity accountToUpdateBalance = new AccountEntity();
        AccountEntity updatedAccount = new AccountEntity();

        Mockito.when(accountRepository.save(accountToUpdateBalance)).thenReturn(updatedAccount);
        assertEquals(updatedBalanceResponse, accountService.updateAccountAvailableBalance(updateBalanceRequest));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(ints = {-1})
    void updateAccountAvailableBalanceInvalidAccountId(int number) {
        //update user account balance
        UpdateBalanceRequest updateBalanceRequest = new UpdateBalanceRequest(number, 10);


        GenericResponse failedResponse= new GenericResponse.GenericResponseBuilder(true)
                .requestType(ResponseType.FAIL)
                .message("Failed to update account balance: Invalid Account Id")
                .build();

        Mockito.when(accountRepository.findById(number)).thenReturn(Optional.empty());
        assertEquals(failedResponse, accountService.updateAccountAvailableBalance(updateBalanceRequest));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void updateAccountAvailableBalanceInvalidAmount(int number) {

        UpdateBalanceRequest updateBalanceRequest = new UpdateBalanceRequest(accountId, number);

        GenericResponse failedResponse= new GenericResponse.GenericResponseBuilder(true)
                .requestType(ResponseType.FAIL)
                .message("Failed to update account available balance: Invalid Amount Requested")
                .build();

        assertEquals(failedResponse, accountService.updateAccountAvailableBalance(updateBalanceRequest));
    }

    @ParameterizedTest
    @ValueSource(ints = {743, 686, 604, 233, 298, -10, 0})
    void updateAccountPendingBalance(int number) {
        UpdateBalanceRequest updateBalanceRequest = new UpdateBalanceRequest(accountId, number);

        GenericResponse successfulUpdateResponse = new GenericResponse.GenericResponseBuilder(true)
                .requestType(ResponseType.UPDATE)
                        .message("Successfully updated account pending balance: " + updateBalanceRequest.getAmount())
                                .build();

        AccountEntity accountUpdatingBalance = new AccountEntity();
        AccountEntity updatedAccount = new AccountEntity();

        Mockito.when(accountRepository.save(accountUpdatingBalance)).thenReturn(updatedAccount);
        assertEquals(successfulUpdateResponse, accountService.updateAccountPendingBalance(updateBalanceRequest));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(ints = {-1})
    void updateAccountPendingBalanceInvalidAccountId(int number) {
        //update user account balance
        UpdateBalanceRequest updateBalanceRequest = new UpdateBalanceRequest(number, 10);


        GenericResponse failedResponse= new GenericResponse.GenericResponseBuilder(true)
                .requestType(ResponseType.FAIL)
                .message("Failed to update account pending balance: Invalid Account Id")
                .build();

        Mockito.when(accountRepository.findById(number)).thenReturn(Optional.empty());
        assertEquals(failedResponse, accountService.updateAccountPendingBalance(updateBalanceRequest));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void updateAccountPendingBalanceInvalidAmount(int number) {

        UpdateBalanceRequest updateBalanceRequest = new UpdateBalanceRequest(accountId, number);
        AccountEntity storedAccount = new AccountEntity();

        GenericResponse failedResponse= new GenericResponse.GenericResponseBuilder(true)
                .requestType(ResponseType.FAIL)
                .message("Failed to update account balance: Invalid Amount Requested")
                .build();

        assertEquals(failedResponse, accountService.updateAccountAvailableBalance(updateBalanceRequest));
    }

    @ParameterizedTest
    @ValueSource(ints = {42, 820, 539, 235, 965})
    void transferToAnotherAccount(int number) {
        TransferRequest transferRequest = new TransferRequest(accountId, otherAccountId, number);
        GenericResponse successfulTransferResponse = new GenericResponse.GenericResponseBuilder(true)
                .requestType(ResponseType.TRANSFER)
                .message("Successful transfer of amount : " + transferRequest.getAmount() +
                        "\nFrom Account: " + transferRequest.getOwnerAccountId() +
                        "\nTo Account: " + transferRequest.getReceiverAccountId())
                .build();

        AccountEntity receiverAccount = new AccountEntity();

        AccountEntity updateOwnerAccountPendingBalance = new AccountEntity();
        AccountEntity updateReceiverAccountPendingBalance = new AccountEntity();

        AccountEntity updatedOwnerAccount = new AccountEntity();
        AccountEntity updatedReceiverAccount = new AccountEntity();

        Mockito.when(accountRepository.save(updateOwnerAccountPendingBalance)).thenReturn(updatedOwnerAccount);
        Mockito.when(accountRepository.save(updateReceiverAccountPendingBalance)).thenReturn(updatedReceiverAccount);

        assertEquals(successfulTransferResponse, accountService.transferToAnotherAccount(transferRequest));
    }

    @Test
    void transferToAnotherAccountInvalidOwnerId(){
        TransferRequest transferRequest = new TransferRequest(-1, otherAccountId, 10);
        GenericResponse failResponse = new GenericResponse.GenericResponseBuilder(false)
                .requestType(ResponseType.FAIL)
                .message("Failed to transfer to account: Owner Account Invalid Id " + transferRequest.getOwnerAccountId())
                .build();

        Mockito.when(accountRepository.findById(transferRequest.getOwnerAccountId())).thenReturn(Optional.empty());
        assertEquals(failResponse, accountService.transferToAnotherAccount(transferRequest));
    }

    @Test
    void transferToAnotherAccountInvalidReceiverId(){
        TransferRequest transferRequest = new TransferRequest(accountId, -1, 10);
        GenericResponse failResponse = new GenericResponse.GenericResponseBuilder(false)
                .requestType(ResponseType.FAIL)
                .message("Failed to transfer to account: Receiver Account Invalid Id " + transferRequest.getReceiverAccountId())
                .build();

        Mockito.when(accountRepository.findById(transferRequest.getReceiverAccountId())).thenReturn(Optional.empty());
        assertEquals(failResponse, accountService.transferToAnotherAccount(transferRequest));
    }

    @Test
    void transferToAnotherAccountInvalidOwnerAmount(){
        TransferRequest transferRequest = new TransferRequest(accountId, otherAccountId, 100000);
        GenericResponse failResponse = new GenericResponse.GenericResponseBuilder(false)
                .requestType(ResponseType.FAIL)
                .message("Failed to transfer to account: Owner balance insufficient for amount" + transferRequest.getAmount())
                .build();

        assertEquals(failResponse, accountService.transferToAnotherAccount(transferRequest));
    }
}