package com.revature.thevault.service.classes;

import com.revature.thevault.presentation.model.request.*;
import com.revature.thevault.presentation.model.response.AccountResponse;
import com.revature.thevault.presentation.model.response.builder.DeleteResponse;
import com.revature.thevault.presentation.model.response.builder.GetResponse;
import com.revature.thevault.presentation.model.response.builder.PutResponse;
import com.revature.thevault.repository.dao.AccountRepository;
import com.revature.thevault.repository.entity.AccountEntity;
import com.revature.thevault.repository.entity.AccountTypeEntity;
import com.revature.thevault.repository.entity.LoginCredentialEntity;
import com.revature.thevault.service.exceptions.InvalidAccountIdException;
import com.revature.thevault.service.exceptions.InvalidAccountTypeException;
import com.revature.thevault.service.exceptions.InvalidAmountException;
import com.revature.thevault.service.exceptions.InvalidUserIdException;
import com.revature.thevault.utility.enums.ResponseType;
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

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
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

    @BeforeAll
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
    void getAccount() {
        Optional<AccountEntity> accountEntityOptional = Optional.of(storedAccount);
        Mockito.when(accountRepository.findById(storedAccount.getPk_account_id())).thenReturn(accountEntityOptional);
        GetResponse goodGetResponse = GetResponse.builder()
                        .success(true)
                                .responseType(ResponseType.GET)
                                        .message("Account retrieved by Account Id: " + storedAccount.getPk_account_id())
                                                .gotObject(accountEntityOptional.get())
                                                        .build();
        GetAccountRequestSingle goodGetAccountRequestSingle = new GetAccountRequestSingle(storedAccount.getPk_account_id());
        assertEquals(goodGetResponse, accountService.getAccount(goodGetAccountRequestSingle));
    }

    @Test
    void createAccountInvalidUserIdRequest(){
        CreateAccountRequest badAccountCreateRequest = new CreateAccountRequest(-1, accountType.get(0));
        AccountEntity badCreatingAccount = new AccountEntity();
        Mockito.when(accountRepository.save(badCreatingAccount)).thenReturn(null);
        assertThrows(InvalidUserIdException.class,() -> accountService.createAccount(badAccountCreateRequest));
    }

    @Test
    void createAccountInvalidAccountTypeRequest(){
        CreateAccountRequest badAccountCreateRequest = new CreateAccountRequest(userId, "lol");
        AccountEntity badCreatingAccount = new AccountEntity();
        Mockito.when(accountRepository.save(badCreatingAccount)).thenReturn(null);
        assertThrows(InvalidAccountTypeException.class, () -> accountService.createAccount(badAccountCreateRequest));
    }

    @Test
    void deleteAccount() {
        DeleteAccountRequest goodDeleteAccountRequest = new DeleteAccountRequest(storedAccount.getPk_account_id());
        DeleteResponse successfulDeleteAccountResponse = DeleteResponse.builder()
                .success(true)
                .responseType(ResponseType.DELETE)
                .message("Successful Account Deletion: " + storedAccount.getPk_account_id())
                .deletedObject(storedAccount)
                .build();
        assertEquals(successfulDeleteAccountResponse, accountService.deleteAccount(goodDeleteAccountRequest));
    }

    @Test
    void deleteAccountInvalidAccountIdUserNotFound() {
        DeleteAccountRequest badAccountDeleteRequest = new DeleteAccountRequest(-1);
        Mockito.when(accountRepository.getById(badAccountDeleteRequest.getAccountId())).thenThrow(EntityNotFoundException.class);
        assertThrows(InvalidAccountIdException.class, () -> accountService.deleteAccount(badAccountDeleteRequest));
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

        AccountEntity accountToUpdateBalance = new AccountEntity();
        AccountEntity updatedAccount = new AccountEntity();

        PutResponse updatedBalanceResponse = PutResponse.builder()
                .success(true)
                .responseType(ResponseType.PUT)
                .message("Successfully updated users available balance: " + updateBalanceRequest.getAmount())
                .updatedObject(updatedAccount)
                .build();

        Mockito.when(accountRepository.save(accountToUpdateBalance)).thenReturn(updatedAccount);
        assertEquals(updatedBalanceResponse, accountService.updateAccountAvailableBalance(updateBalanceRequest));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1})
    void updateAccountAvailableBalanceInvalidAccountId(int number) {
        //update user account balance
        UpdateBalanceRequest updateBalanceRequest = new UpdateBalanceRequest(number, 10);

        Mockito.when(accountRepository.findById(number)).thenReturn(Optional.empty());
        assertThrows(InvalidAccountIdException.class, () -> accountService.updateAccountAvailableBalance(updateBalanceRequest));
    }

    @ParameterizedTest
    @ValueSource(ints ={0, 10000000} )
    void updateAccountAvailableBalanceInvalidAmount(int number) {

        UpdateBalanceRequest updateBalanceRequest = new UpdateBalanceRequest(accountId, number);

        assertThrows(InvalidAmountException.class, () -> accountService.updateAccountAvailableBalance(updateBalanceRequest));
    }

    @ParameterizedTest
    @ValueSource(ints = {743, 686, 604, 233, 298, -10, 0})
    void updateAccountPendingBalance(int number) {
        UpdateBalanceRequest updateBalanceRequest = new UpdateBalanceRequest(accountId, number);

        AccountEntity accountUpdatingBalance = new AccountEntity();
        AccountEntity updatedAccount = new AccountEntity();

        PutResponse successfulPutResponse = PutResponse.builder()
                .success(true)
                .responseType(ResponseType.PUT)
                .message("Successfully updated account pending balance: " + updateBalanceRequest.getAmount())
                .updatedObject(updatedAccount)
                .build();

        Mockito.when(accountRepository.save(accountUpdatingBalance)).thenReturn(updatedAccount);
        assertEquals(successfulPutResponse, accountService.updateAccountPendingBalance(updateBalanceRequest));
    }

    @Test
    void updateAccountPendingBalanceInvalidAccountId() {
        //update user account balance
        UpdateBalanceRequest updateBalanceRequest = new UpdateBalanceRequest(-1, 10);
        Mockito.when(accountRepository.findById(updateBalanceRequest.getAccountId())).thenReturn(Optional.empty());
        assertThrows(InvalidAccountIdException.class,() -> accountService.updateAccountPendingBalance(updateBalanceRequest));
    }

    @ParameterizedTest
    @ValueSource(ints ={0, 10000000} )
    void updateAccountPendingBalanceInvalidAmount(int number) {
        UpdateBalanceRequest updateBalanceRequest = new UpdateBalanceRequest(accountId, number);
        AccountEntity storedAccount = new AccountEntity();
        assertThrows(InvalidAmountException.class, () -> accountService.updateAccountAvailableBalance(updateBalanceRequest));
    }

    @ParameterizedTest
    @ValueSource(ints = {42, 820, 539, 235, 965})
    void transferToAnotherAccount(int number) {
        TransferRequest transferRequest = new TransferRequest(accountId, otherAccountId, number);
        AccountEntity receiverAccount = new AccountEntity();

        AccountEntity updateOwnerAccountPendingBalance = new AccountEntity();
        AccountEntity updateReceiverAccountPendingBalance = new AccountEntity();

        AccountEntity updatedOwnerAccount = new AccountEntity();
        AccountEntity updatedReceiverAccount = new AccountEntity();
        PutResponse successfulTransferResponse = PutResponse.builder()
                .success(true)
                .responseType(ResponseType.PUT)
                .message("Successful transfer of amount : " + transferRequest.getAmount() +
                        "\nFrom Account: " + transferRequest.getOwnerAccountId() +
                        "\nTo Account: " + transferRequest.getReceiverAccountId())
                .updatedObject(Arrays.asList(updatedReceiverAccount, updatedOwnerAccount))
                .build();

        Mockito.when(accountRepository.save(updateOwnerAccountPendingBalance)).thenReturn(updatedOwnerAccount);
        Mockito.when(accountRepository.save(updateReceiverAccountPendingBalance)).thenReturn(updatedReceiverAccount);
        assertEquals(successfulTransferResponse, accountService.transferToAnotherAccount(transferRequest));
    }

    @Test
    void transferToAnotherAccountInvalidOwnerId(){
        TransferRequest transferRequest = new TransferRequest(-1, otherAccountId, 10);
        Mockito.when(accountRepository.findById(transferRequest.getOwnerAccountId())).thenReturn(Optional.empty());
        assertThrows(InvalidAccountIdException.class, () -> accountService.transferToAnotherAccount(transferRequest));
    }

    @Test
    void transferToAnotherAccountInvalidReceiverId(){
        TransferRequest transferRequest = new TransferRequest(accountId, -1, 10);
        Mockito.when(accountRepository.findById(transferRequest.getReceiverAccountId())).thenReturn(Optional.empty());
        assertThrows(InvalidAccountIdException.class, () -> accountService.transferToAnotherAccount(transferRequest));
    }

    @Test
    void transferToAnotherAccountInvalidOwnerAmount(){
        TransferRequest transferRequest = new TransferRequest(accountId, otherAccountId, 100000);
        assertThrows(InvalidAmountException.class, () -> accountService.transferToAnotherAccount(transferRequest));
    }

}