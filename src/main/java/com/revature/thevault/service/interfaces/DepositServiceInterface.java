package com.revature.thevault.service.interfaces;

import com.revature.thevault.presentation.model.request.DepositRequest;

import com.revature.thevault.presentation.model.response.builder.GetResponse;
import com.revature.thevault.presentation.model.response.builder.PostResponse;

public interface DepositServiceInterface {

    // Receive a deposit request from user, return a successful response.
    PostResponse createDeposit(DepositRequest depositRequest);

    // Get all of the users deposits from their accountId;
    GetResponse getAllUserDeposits(int accountId);

    // Get all of their deposits by specific type of deposit
    GetResponse getAlLUserDepositsOfType(int accountId, String depositTypeId);

}
