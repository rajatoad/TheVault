package com.revature.thevault.service.interfaces;

import com.revature.thevault.repository.dao.presentation.model.request.UpdateWithdrawRequest;
import com.revature.thevault.repository.dao.presentation.model.request.WithdrawRequest;
import com.revature.thevault.repository.dao.presentation.model.response.UpdateWithdrawResponse;
import com.revature.thevault.repository.dao.presentation.model.response.WithdrawResponse;
import com.revature.thevault.repository.entity.WithdrawEntity;

import java.util.List;

public interface WithdrawServiceInterface {
    // Receive a withdraw request from user, return a successful response.
    WithdrawResponse createWithdrawal(WithdrawRequest withdrawRequest);

    // Get all of the users withdrawal from their accountId;
    List<WithdrawEntity> getAllUserWithdrawals(int accountId);

    // Get all of their withdrawals by specific type of withdrawals
    List<WithdrawEntity> getAlLUserWithdrawalsOfType(int accountId, int requestTypeId);

    // Get all of their withdrawals of user by status
    List<WithdrawEntity> getAllUserWithdrawalsOfStatus(int accountId, int requestStatusId);

    // Get all of their withdrawals by type and status
    List<WithdrawEntity> getAllUserWithdrawalsOfTypeAndStatus(int accountId, int requestTypeId, int requestStatusId);

    // Update a withdraw request in the database
    UpdateWithdrawResponse updateWithdrawalStatus(UpdateWithdrawRequest updateWithdrawRequest);

}
