package com.revature.thevault.service.classes;

import com.revature.thevault.presentation.model.request.*;
import com.revature.thevault.presentation.model.response.builder.DeleteResponse;
import com.revature.thevault.presentation.model.response.builder.GetResponse;
import com.revature.thevault.presentation.model.response.builder.PostResponse;
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
import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.persistence.EntityNotFoundException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @MockBean
    private AccountRepository accountRepository;

    @MockBean
    private AccountTypeService accountTypeService;

    private AccountEntity storedAccount;
    private AccountEntity storedAccountTransferring;
    private Optional<AccountEntity> optionalAccount;
    private Optional<AccountEntity> optionalAccountTransferring;

    private int userId;
    private int otherUserId;
    private int accountId;
    private List<String> accountType;

    private int otherAccountId;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        userId = 1;
        otherUserId = 2;
        accountId = 1;
        otherAccountId = 2;
        accountType = new ArrayList<>();
        accountType.add("Checking");
        accountType.add("Savings");

        LoginCredentialEntity primaryLogin = new LoginCredentialEntity(userId, "username", "password");
        AccountTypeEntity accountTypeEntity = new AccountTypeEntity(1, "Checking");

        LoginCredentialEntity secondaryLogin = new LoginCredentialEntity(otherUserId, "user2", "pass2");


        storedAccount = new AccountEntity(accountId, primaryLogin, accountTypeEntity, 100L, 120L);
        optionalAccount = Optional.of(storedAccount);

        storedAccountTransferring = new AccountEntity(otherAccountId, secondaryLogin, accountTypeEntity, 100L, 120L);
        optionalAccountTransferring = Optional.of(storedAccountTransferring);

        Mockito.when(accountRepository.findById(accountId)).thenReturn(optionalAccount);
        Mockito.when(accountTypeService.findAccountTypeEntityByName(accountType.get(0))).thenReturn(new AccountTypeEntity(1, accountType.get(0)));
        Mockito.when(accountTypeService.findAccountTypeEntityByName(accountType.get(1))).thenReturn(new AccountTypeEntity(2, accountType.get(1)));
        Mockito.when(accountRepository.findByLogincredentials(new LoginCredentialEntity(userId, "", ""))).thenReturn(Collections.singletonList(storedAccount));
        Mockito.when(accountRepository.findById(otherAccountId)).thenReturn(optionalAccountTransferring);
    }

    @Test
    void createAccount() {
        CreateAccountRequest goodAccountCreateRequest = new CreateAccountRequest(userId, accountType.get(0));
        AccountEntity creatingAccount = new AccountEntity(
            0,
            new LoginCredentialEntity(storedAccount.getLogincredentials().getPk_user_id(), "",""),
            storedAccount.getAccountTypeEntity(),
            0,
                0
        );
        Mockito.when(accountRepository.save(creatingAccount)).thenReturn(storedAccount);

        PostResponse successfulAccountResponse = PostResponse.builder()
                .success(true)
                        .responseType(ResponseType.POST)
                                .message("Successful Account Creation")
                                        .createdObject(Collections.singletonList(storedAccount))
                                                .build();
        assertEquals(successfulAccountResponse, accountService.createAccount(goodAccountCreateRequest));
    }

    @Test
    void createAccountInvalidAccountType(){
        CreateAccountRequest badAccountCreateRequest = new CreateAccountRequest(userId, "lol");
        Mockito.when(accountTypeService.findAccountTypeEntityByName(badAccountCreateRequest.getAccountType())).thenThrow(InvalidAccountTypeException.class);
        assertThrows(InvalidAccountTypeException.class, () -> accountService.createAccount(badAccountCreateRequest));
    }

    @Test
    void getAccount() {
        GetResponse goodGetResponse = GetResponse.builder()
                        .success(true)
                                .responseType(ResponseType.GET)
                                        .message("Account retrieved by Account Id: " + storedAccount.getPk_account_id())
                                                .gotObject(Collections.singletonList(optionalAccount.get()))
                                                        .build();
        assertEquals(goodGetResponse, accountService.getAccount(storedAccount.getPk_account_id()));
    }

    @Test
    void getAccountInvalidAccountId(){
        assertThrows(InvalidAccountIdException.class, () -> accountService.getAccount(-1));
    }

    @Test
    void deleteAccount() {
        DeleteResponse successfulDeleteAccountResponse = DeleteResponse.builder()
                .success(true)
                .responseType(ResponseType.DELETE)
                .message("Successful Account Deletion: " + storedAccount.getPk_account_id())
                .deletedObject(Collections.singletonList(storedAccount))
                .build();

        Mockito.doNothing().when(accountRepository).delete(storedAccount);
        assertEquals(successfulDeleteAccountResponse, accountService.deleteAccount(storedAccount.getPk_account_id()));
    }

    @Test
    void deleteAccountInvalidAccountIdNotFound() {
        assertThrows(InvalidAccountIdException.class, () -> accountService.deleteAccount(-1));
    }

    @Test
    void getAccounts() {
        GetResponse getResponse = GetResponse.builder()
                        .success(true)
                                .responseType(ResponseType.GET)
                                        .message("Successful retrieval of user accounts by user Id: " + userId)
                                                .gotObject(Collections.singletonList(storedAccount))
                                                        .build();
        assertEquals(getResponse, accountService.getAccounts(userId));
    }

    @Test
    void getAccountsInvalidUserId() {
        Mockito.when(accountRepository.findByLogincredentials(new LoginCredentialEntity(-1, "", ""))).thenThrow(IllegalArgumentException.class);
        assertThrows(InvalidUserIdException.class, () -> accountService.getAccounts(-1));
    }

    @Test
    void updateAccountAvailableBalance() {
        UpdateAccountRequest updateAccountRequest = new UpdateAccountRequest(accountId, accountType.get(0), 100L, 100L);
        AccountEntity updatedAccount = new AccountEntity(
                storedAccount.getPk_account_id(),
                storedAccount.getLogincredentials(),
                storedAccount.getAccountTypeEntity(),
                updateAccountRequest.getAvailableBalance(),
                updateAccountRequest.getPendingBalance()
        );

        PutResponse updatedBalanceResponse = PutResponse.builder()
                .success(true)
                .responseType(ResponseType.PUT)
                .message("Successfully updated users available balance: " + updateAccountRequest.getAvailableBalance())
                .updatedObject(Collections.singletonList(updatedAccount))
                .build();

        Mockito.when(accountRepository.save(updatedAccount)).thenReturn(updatedAccount);
        assertEquals(updatedBalanceResponse, accountService.updateAccount(updateAccountRequest));
    }

    @Test
    void updateAccountInvalidAccountId() {
        assertThrows(InvalidAccountIdException.class, () -> accountService.updateAccount(new UpdateAccountRequest(-1, "", 0, 0)));
    }

    @Test
    void transferToAnotherAccount() {
        TransferRequest transferRequest = new TransferRequest(accountId, otherAccountId, 10L);

        AccountEntity updatedOwnerAccount = new AccountEntity(
                storedAccount.getPk_account_id(),
                storedAccount.getLogincredentials(),
                storedAccount.getAccountTypeEntity(),
                storedAccount.getAvailable_balance() - transferRequest.getAmount(),
                storedAccount.getPending_balance() - transferRequest.getAmount()
        );

        AccountEntity updatedReceiverAccount = new AccountEntity(
                storedAccountTransferring.getPk_account_id(),
                storedAccountTransferring.getLogincredentials(),
                storedAccountTransferring.getAccountTypeEntity(),
                storedAccountTransferring.getAvailable_balance(),
                storedAccountTransferring.getPending_balance() + transferRequest.getAmount()
        );

        Mockito.when(accountRepository.save(updatedOwnerAccount)).thenReturn(updatedOwnerAccount);
        Mockito.when(accountRepository.save(updatedReceiverAccount)).thenReturn(updatedReceiverAccount);

        PutResponse successfulTransferResponse = PutResponse.builder()
                .success(true)
                .responseType(ResponseType.PUT)
                .message("Successfully Transferred Amount: $" + transferRequest.getAmount() + " From Account Id: " + updatedOwnerAccount.getPk_account_id() + " To Account Id: " + updatedReceiverAccount.getPk_account_id())
                .updatedObject(Arrays.asList(updatedOwnerAccount, updatedReceiverAccount))
                .build();

        assertEquals(successfulTransferResponse, accountService.transferToAnotherAccount(transferRequest));
    }

    @Test
    void transferToAnotherAccountInvalidOwnerId(){
        TransferRequest transferRequest = new TransferRequest(-1, otherAccountId, 10);
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
        TransferRequest transferRequest = new TransferRequest(accountId, otherAccountId, 100000L);
        assertThrows(InvalidAmountException.class, () -> accountService.transferToAnotherAccount(transferRequest));
    }

}