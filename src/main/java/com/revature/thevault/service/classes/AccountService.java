package com.revature.thevault.service.classes;

import com.revature.thevault.presentation.model.request.*;
import com.revature.thevault.presentation.model.response.builder.*;
import com.revature.thevault.repository.dao.AccountRepository;
import com.revature.thevault.repository.entity.AccountEntity;
import com.revature.thevault.repository.entity.LoginCredentialEntity;
import com.revature.thevault.service.exceptions.*;
import com.revature.thevault.service.interfaces.AccountServiceInterface;
import com.revature.thevault.utility.enums.ResponseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.security.auth.login.AccountNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service("accountService")
public class AccountService implements AccountServiceInterface{

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountTypeService accountTypeService;

    @Override
    public PostResponse createAccount(CreateAccountRequest createAccountRequest) {
        try {
            return PostResponse.builder()
                    .success(true)
                    .responseType(ResponseType.POST)
                    .message("Successful Account Creation")
                    .createdObject(Collections.singletonList(
                            accountRepository.save(
                                    new AccountEntity(
                                            0,
                                            new LoginCredentialEntity(createAccountRequest.getUserId(), "", ""),
                                            accountTypeService.findAccountTypeEntityByName(createAccountRequest.getAccountType()),
                                            0,
                                            0
                                    )
                            )
                    ))
                    .build();
        }catch(InvalidAccountTypeException e){
            throw e;
        }catch (Exception e){
            throw new InvalidRequestException(HttpStatus.BAD_REQUEST, "Invalid Request: " + e);
        }
    }

    @Override
    public GetResponse getAccount(int accountId) {
        try {
            return GetResponse.builder()
                    .success(true)
                    .responseType(ResponseType.GET)
                    .message("Account retrieved by Account Id: " + accountId)
                    .gotObject(Collections.singletonList(getAccountById(accountId)))
                    .build();
        }catch(InvalidAccountIdException e){
            throw e;
        }catch(Exception e){
            throw new InvalidRequestException(HttpStatus.BAD_REQUEST, "Invalid Request");
        }
    }

    @Override
    public DeleteResponse deleteAccount(int accountId) {
        try{
            AccountEntity accountEntity = getAccountById(accountId);
            accountRepository.delete(accountEntity);
            return DeleteResponse.builder()
                    .success(true)
                    .responseType(ResponseType.DELETE)
                    .message("Successful Account Deletion: " + accountId)
                    .deletedObject(Collections.singletonList(accountEntity))
                    .build();
        }catch(InvalidAccountIdException e){
            throw new InvalidAccountIdException(HttpStatus.BAD_REQUEST, "Invalid Account Id for delete request: " + accountId);
        }catch(Exception e){
            throw new InvalidRequestException(HttpStatus.BAD_REQUEST, "Invalid Request");
        }
    }


    @Override
    public GetResponse getAccounts(int userId) {
        try {
            List<AccountEntity> accountEntities = getUserAccountsByUserId(userId);
            return GetResponse.builder()
                    .success(true)
                    .responseType(ResponseType.GET)
                    .message("Successful retrieval of user accounts by user Id: " + userId)
                    .gotObject(accountEntities)
                    .build();
        }catch(InvalidUserIdException e){
            throw e;
        }catch (EntityNotFoundException e){
            throw new InvalidRequestException(HttpStatus.BAD_REQUEST, "Accounts not Found for User: " + userId);
        }catch(Exception e){
            throw new InvalidRequestException(HttpStatus.BAD_REQUEST, "Invalid Request");
        }
    }

    @Override
    public PutResponse updateAccount(UpdateAccountRequest updateAccountRequest) {
        try {
            AccountEntity accountEntityReal = getAccountById(updateAccountRequest.getAccountId());
            accountEntityReal.setAccountTypeEntity(accountTypeService.findAccountTypeEntityByName(updateAccountRequest.getAccountType()));
            accountEntityReal.setAvailable_balance(updateAccountRequest.getAvailableBalance());
            accountEntityReal.setPending_balance(updateAccountRequest.getPendingBalance());
            AccountEntity updatedAccountEntity = updateAccountEntity(accountEntityReal);
            return PutResponse.builder()
                    .success(true)
                    .responseType(ResponseType.PUT)
                    .message("Successfully Updated Account")
                    .updatedObject(Collections.singletonList(updatedAccountEntity))
                    .build();
        }catch(InvalidAccountIdException e){
            throw e;
        }catch (Exception e){
            throw new InvalidRequestException(HttpStatus.BAD_REQUEST, "Invalid Request");
        }
    }

    @Override
    public PutResponse transferToAnotherAccount(TransferRequest transferRequest) {
        try {
            AccountEntity ownerAccountEntity = getAccountById(transferRequest.getOwnerAccountId());
            AccountEntity receiverAccountEntity = getAccountById(transferRequest.getReceiverAccountId());
            if (ownerAccountEntity.getAvailable_balance() <= transferRequest.getAmount())
                throw new InvalidAmountException(HttpStatus.NOT_ACCEPTABLE, "Owner Account: $" + ownerAccountEntity.getAvailable_balance() + " Invalid Requested Amount: $" + transferRequest.getAmount());

            AccountEntity updatedOwner = updateAccountEntity(
                    updateAmount(ownerAccountEntity, -transferRequest.getAmount(), true));
            AccountEntity updatedReceiver = updateAccountEntity(
                    updateAmount(receiverAccountEntity, transferRequest.getAmount(), false));

            return PutResponse.builder()
                    .success(true)
                    .responseType(ResponseType.PUT)
                    .message("Successfully Transferred Amount: $" + transferRequest.getAmount() + " From Account Id: " + ownerAccountEntity.getPk_account_id() + " To Account Id: " + receiverAccountEntity.getPk_account_id())
                    .updatedObject(Arrays.asList(updatedOwner, updatedReceiver))
                    .build();
        }catch (InvalidAccountIdException | InvalidAmountException e) {
            throw e;
        } catch (Exception e){
            throw new InvalidRequestException(HttpStatus.BAD_REQUEST, "Invalid Request: " + e);
        }
    }

    /**
     * Update the balance of an account entity, it is always assumed that the pending balance is updated but a boolean must be true to update both
     * If money is to be removed then a negative amount must be passed in
     * @param accountEntity The account entity passed in to be updated
     * @param amount The amount needed to be updating by, pass in a negative if money is to be removed
     * @param both The boolean check to update both pending and available balance.
     * @return The account entity with its balances updated as requested
     */
     private AccountEntity updateAmount(AccountEntity accountEntity, long amount, boolean both){
        accountEntity.setPending_balance(accountEntity.getPending_balance() + amount);
        if(both) accountEntity.setAvailable_balance(accountEntity.getAvailable_balance() + amount);
        return accountRepository.save(accountEntity);
    }

//
//    //Generic Fail Response used by the service methods for exceptions that occurred but were not caught explicitly
//    //This will provide more information than a generic exception for the front end
//
//    private GenericResponse failResponse(Exception e, String message) {
//        return FailResponse.builder()
//                .success(false)
//                .responseType(ResponseType.FAIL)
//                .message(message)
//                .exception(e)
//                .build();
//    }

    private AccountEntity getAccountById(int accountId){
        try {
            Optional<AccountEntity> accountEntityOptional = accountRepository.findById(accountId);
            if (accountEntityOptional.isPresent()) return accountEntityOptional.get();
            else throw new EntityNotFoundException();
        }catch(Exception e){
            throw new InvalidAccountIdException(HttpStatus.BAD_REQUEST, "Invalid Account Id Provided: " + accountId);
        }
    }

    private List<AccountEntity> getUserAccountsByUserId(int userId){
        try {
            return accountRepository.findByLogincredentials(new LoginCredentialEntity(userId, "", ""));
        }catch(Exception e){
            throw new InvalidUserIdException(HttpStatus.BAD_REQUEST, "Invalid User Id Provided: " + userId);
        }
    }

    private AccountEntity updateAccountEntity(AccountEntity accountEntity){
        return accountRepository.save(accountEntity);
    }
}
