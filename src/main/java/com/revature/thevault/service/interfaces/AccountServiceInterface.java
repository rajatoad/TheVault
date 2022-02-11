package com.revature.thevault.service.interfaces;

import com.revature.thevault.presentation.model.request.*;
import com.revature.thevault.presentation.model.response.AccountResponse;
import com.revature.thevault.presentation.model.response.builder.DeleteResponse;
import com.revature.thevault.presentation.model.response.builder.GenericResponse;
import com.revature.thevault.presentation.model.response.builder.GetResponse;
import com.revature.thevault.presentation.model.response.builder.PostResponse;
import com.revature.thevault.repository.entity.AccountEntity;

import java.util.List;

public interface AccountServiceInterface {

    // Method responsible for creating a request
    GenericResponse createAccount(CreateAccountRequest createAccountRequest);

    GetResponse getAccount(GetAccountRequestSingle getAccountRequestSingle);

    // Delete an account
    DeleteResponse deleteAccount(DeleteAccountRequest deleteAccountRequest);

    // Get users accounts
    GetResponse getAccounts(GetAccountRequestAll getAccountRequestAll);

    // Update accounts available balance
    GenericResponse updateAccountAvailableBalance(UpdateBalanceRequest updateBalanceRequest);

    // Update accounts pending balance
    GenericResponse updateAccountPendingBalance(UpdateBalanceRequest updateBalanceRequest);

    // Transfer from one account to another
    GenericResponse transferToAnotherAccount(TransferRequest transferRequest);

}
