package com.revature.thevault.service.interfaces;

import com.revature.thevault.presentation.model.request.CreateAccountRequest;
import com.revature.thevault.presentation.model.request.DeleteAccountRequest;
import com.revature.thevault.presentation.model.request.TransferRequest;
import com.revature.thevault.presentation.model.request.UpdateBalanceRequest;
import com.revature.thevault.presentation.model.response.AccountResponse;
import com.revature.thevault.presentation.model.response.GenericResponse;
import com.revature.thevault.repository.entity.AccountEntity;

import java.util.List;

public interface AccountServiceInterface {

    // Method responsible for creating a request
    AccountResponse createAccount(CreateAccountRequest createAccountRequest);

    // Delete an account
    GenericResponse deleteAccount(DeleteAccountRequest deleteAccountRequest);

    // Get users accounts
    List<AccountEntity> getAccounts(int userId);

    // Update accounts available balance
    GenericResponse updateAccountAvailableBalance(UpdateBalanceRequest updateBalanceRequest);

    // Update accounts pending balance
    GenericResponse updateAccountPendingBalance(UpdateBalanceRequest updateBalanceRequest);

    // Transfer from one account to another
    GenericResponse transferToAnotherAccount(TransferRequest transferRequest);
}
