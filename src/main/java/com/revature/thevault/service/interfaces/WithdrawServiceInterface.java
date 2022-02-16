package com.revature.thevault.service.interfaces;

import com.revature.thevault.presentation.model.request.WithdrawRequest;
import com.revature.thevault.presentation.model.response.builder.GetResponse;
import com.revature.thevault.presentation.model.response.builder.PostResponse;

public interface WithdrawServiceInterface {
    // Receive a withdraw request from user, return a successful response.
    PostResponse createWithdrawal(WithdrawRequest withdrawRequest);

    // Get all of the users withdrawal from their accountId;
    GetResponse getAllUserWithdrawals(int accountId);

    // Get all of their withdrawals by specific type of withdrawals
    GetResponse getAlLUserWithdrawalsOfType(int accountId, String requestTypeId);

    GetResponse findByWithdrawId(int withdrawId);
}
