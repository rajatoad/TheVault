package com.revature.thevault.service.classes;

import com.revature.thevault.presentation.model.request.*;
import com.revature.thevault.presentation.model.response.AccountResponse;
import com.revature.thevault.presentation.model.response.builder.*;
import com.revature.thevault.repository.dao.AccountRepository;
import com.revature.thevault.repository.entity.AccountEntity;
import com.revature.thevault.service.exceptions.InvalidAccountIdException;
import com.revature.thevault.service.interfaces.AccountServiceInterface;
import com.revature.thevault.utility.enums.ResponseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

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
            return new GetResponse.Builder(true, ResponseType.GET, ("Account retrieved by Id: " + getAccountRequestSingle.getAccountId()))
                    .gotObject(accountRepository.findById(getAccountRequestSingle.getAccountId()).get())
                    .build();
        }catch(EntityNotFoundException e){
            throw new InvalidAccountIdException(HttpStatus.BAD_REQUEST, "Invalid Account Id for get request: " + getAccountRequestSingle.getAccountId());
        }catch(Exception e){
            return null;
        }
    }

    @Override
    public DeleteResponse deleteAccount(DeleteAccountRequest deleteAccountRequest) {
        try{
            AccountEntity accountEntity = accountRepository.getById(deleteAccountRequest.getAccountId());
            accountRepository.delete(accountEntity);
            return new DeleteResponse.Builder(
                    true,
                    ResponseType.DELETE,
                    ("Successful Account Deletion: " + deleteAccountRequest.getAccountId())
                    ).deletedObject(accountEntity)
                    .build();
        }catch(EntityNotFoundException e){
            throw new InvalidAccountIdException(HttpStatus.BAD_REQUEST, "Invalid Account Id for delete request: " + deleteAccountRequest.getAccountId());
        }catch(Exception e){
//            return failResponse(e, "Failed to delete account");
            return null;
        }
    }


    @Override
    public GetResponse getAccounts(GetAccountRequestAll getAccountRequestAll) {
        return null;
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
        return new FailResponse.Builder(
                true,
                ResponseType.FAIL,
                (message)
        ).exception(e).
                build();
    }
}
