package com.revature.thevault.service.interfaces;

import com.revature.thevault.presentation.model.request.*;
import com.revature.thevault.presentation.model.response.AccountResponse;
import com.revature.thevault.presentation.model.response.builder.DeleteResponse;
import com.revature.thevault.presentation.model.response.builder.GenericResponse;
import com.revature.thevault.presentation.model.response.builder.GetResponse;
import com.revature.thevault.presentation.model.response.builder.PostResponse;
import com.revature.thevault.presentation.model.response.builder.PutResponse;
import com.revature.thevault.repository.entity.AccountEntity;

import java.util.List;

public interface AccountServiceInterface {

    // Method responsible for creating a request
    PostResponse createAccount(CreateAccountRequest createAccountRequest);

    GetResponse getAccount(int accountId);

    // Delete an account
    DeleteResponse deleteAccount(int accountId);

    // Get users accounts
    GetResponse getAccounts(int userId);

    // Update account
    PutResponse updateAccount(UpdateAccountRequest updateAccountRequest);

    // Transfer from one account to another
    PutResponse transferToAnotherAccount(TransferRequest transferRequest);
}
