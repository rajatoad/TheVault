package com.revature.thevault.service.classes;

import com.revature.thevault.presentation.model.request.CreateAccountRequest;
import com.revature.thevault.presentation.model.request.DeleteAccountRequest;
import com.revature.thevault.presentation.model.request.TransferRequest;
import com.revature.thevault.presentation.model.request.UpdateBalanceRequest;
import com.revature.thevault.presentation.model.response.AccountResponse;
import com.revature.thevault.presentation.model.response.GenericResponse;
import com.revature.thevault.repository.dao.AccountRepository;
import com.revature.thevault.repository.entity.AccountEntity;
import com.revature.thevault.service.interfaces.AccountServiceInterface;
import com.revature.thevault.utility.enums.ResponseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service("accountService")
public class AccountService implements AccountServiceInterface{

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public AccountResponse createAccount(CreateAccountRequest createAccountRequest) {
        return null;
    }

    @Override
    public GenericResponse deleteAccount(DeleteAccountRequest deleteAccountRequest) {
        try{
            AccountEntity accountEntity = accountRepository.getById(deleteAccountRequest.getAccountId());
            accountRepository.delete(accountEntity);
            return new GenericResponse.GenericResponseBuilder(true)
                    .requestType(ResponseType.DELETE)
                    .message("Successful Account Deletion: " + deleteAccountRequest.getAccountId())
                    .build();
        }catch(EntityNotFoundException e){
            return failResponse("Failed to delete account: Entity Not Found");
        }catch(Exception e){
            return failResponse("Failed to delete account");
        }
    }

    private GenericResponse failResponse(String message) {
        return new GenericResponse.GenericResponseBuilder(false)
                .requestType(ResponseType.FAIL)
                .message(message)
                .build();
    }

    @Override
    public List<AccountEntity> getAccounts(int userId) {
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
}
