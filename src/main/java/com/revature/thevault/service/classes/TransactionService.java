package com.revature.thevault.service.classes;

import com.revature.thevault.presentation.model.response.builder.GetResponse;
import com.revature.thevault.service.interfaces.TransactionServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("transactionService")
public class TransactionService implements TransactionServiceInterface {

    @Autowired
    private DepositService depositService;

    @Autowired
    private WithdrawService withdrawService;

    @Override
    public GetResponse getTransactionHistory(Integer id) {
        List<De>
    }
}
