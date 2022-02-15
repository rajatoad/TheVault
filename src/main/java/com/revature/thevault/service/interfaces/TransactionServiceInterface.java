package com.revature.thevault.service.interfaces;

import com.revature.thevault.presentation.model.response.builder.GetResponse;

public interface TransactionServiceInterface {
    GetResponse getTransactionHistory(Integer id);
}
