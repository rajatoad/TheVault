package com.revature.thevault.service.interfaces;

import com.revature.thevault.presentation.model.request.DepositRequest;

import com.revature.thevault.presentation.model.response.DepositResponse;
import com.revature.thevault.presentation.model.response.builder.GetResponse;
import com.revature.thevault.presentation.model.response.builder.PostResponse;


import java.util.List;

public interface DepositServiceInterface {

    // Receive a deposit request from user, return a successful response.
    PostResponse createDeposit(DepositRequest depositRequest);

    // Get all of the users deposits from their accountId;
    GetResponse getAllUserDeposits(int accountId);

    // Get all of their deposits by specific type of deposit
    GetResponse getAlLUserDepositsOfType(int accountId, int depositTypeId);

    // get all deposits in the database
    GetResponse  getAllDeposits();

    // get all deposits by type
    GetResponse  getAllDepositsOfType(int depositTypeId);

}
