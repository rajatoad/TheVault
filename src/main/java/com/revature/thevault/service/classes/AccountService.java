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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return null;
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
