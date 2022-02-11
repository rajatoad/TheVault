package com.revature.thevault.service.classes;

import com.revature.thevault.presentation.model.request.*;
import com.revature.thevault.presentation.model.response.AccountResponse;
import com.revature.thevault.presentation.model.response.builder.*;
import com.revature.thevault.repository.dao.AccountRepository;
import com.revature.thevault.repository.entity.AccountEntity;
import com.revature.thevault.repository.entity.LoginCredentialEntity;
import com.revature.thevault.service.exceptions.InvalidAccountIdException;
import com.revature.thevault.service.exceptions.InvalidRequestException;
import com.revature.thevault.service.exceptions.InvalidUserIdException;
import com.revature.thevault.service.interfaces.AccountServiceInterface;
import com.revature.thevault.utility.enums.ResponseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service("accountService")
public class AccountService implements AccountServiceInterface{

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public GenericResponse createAccount(CreateAccountRequest createAccountRequest) {

        return null;
    }

    @Override
    public GetResponse getAccount(GetAccountRequestSingle getAccountRequestSingle) {
        try{
            Optional<AccountEntity> accountEntityOptional = accountRepository.findById(getAccountRequestSingle.getAccountId());
            if(accountEntityOptional.isPresent())
                return GetResponse.builder()
                        .success(true)
                        .responseType(ResponseType.GET)
                        .message("Account retrieved by Account Id: " + getAccountRequestSingle.getAccountId())
                        .gotObject(accountEntityOptional.get())
                        .build();
            else
                throw new InvalidAccountIdException(HttpStatus.BAD_REQUEST, "Invalid Account Id for get request: " + getAccountRequestSingle.getAccountId());
        }catch(Exception e){
            throw new InvalidRequestException(HttpStatus.BAD_REQUEST, "Invalid Request");
        }
    }

    @Override
    public DeleteResponse deleteAccount(DeleteAccountRequest deleteAccountRequest) {
        try{
            AccountEntity accountEntity = accountRepository.getById(deleteAccountRequest.getAccountId());
            accountRepository.delete(accountEntity);
            return DeleteResponse.builder()
                    .success(true)
                    .responseType(ResponseType.DELETE)
                    .message("Successful Account Deletion: " + deleteAccountRequest.getAccountId())
                    .deletedObject(accountEntity)
                    .build();
        }catch(EntityNotFoundException e){
            throw new InvalidAccountIdException(HttpStatus.BAD_REQUEST, "Invalid Account Id for delete request: " + deleteAccountRequest.getAccountId());
        }catch(Exception e){
            throw new InvalidRequestException(HttpStatus.BAD_REQUEST, "Invalid Request");
        }
    }


    @Override
    public GetResponse getAccounts(GetAccountRequestAll getAccountRequestAll) {
        try{
            List<AccountEntity> accountEntities = accountRepository.findByLogincredentials(new LoginCredentialEntity(getAccountRequestAll.getUserId(), "", ""));
            return GetResponse.builder()
                    .success(true)
                    .responseType(ResponseType.GET)
                    .message("Successful retrieval of user accounts by user Id: " + getAccountRequestAll.getUserId())
                    .gotObject(accountEntities)
                    .build();
        }catch(Exception e){
            throw new InvalidRequestException(HttpStatus.BAD_REQUEST, "Invalid Request");
        }
    }

    @Override
    public GenericResponse updateAccountAvailableBalance(UpdateBalanceRequest updateBalanceRequest) {
        return null;
    }

    @Override
    public GenericResponse updateAccountPendingBalance(UpdateBalanceRequest updateBalanceRequest) {
        return null;
    }

    @Override
    public GenericResponse transferToAnotherAccount(TransferRequest transferRequest) {
        return null;
    }


    //Generic Fail Response used by the service methods for exceptions that occurred but were not caught explicitly
    //This will provide more information than a generic exception for the front end

    private GenericResponse failResponse(Exception e, String message) {
        return FailResponse.builder()
                .success(false)
                .responseType(ResponseType.FAIL)
                .message(message)
                .exception(e).
                build();
    }
}
