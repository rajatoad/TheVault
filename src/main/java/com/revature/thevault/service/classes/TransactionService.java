package com.revature.thevault.service.classes;

import com.revature.thevault.presentation.model.response.builder.GetResponse;
import com.revature.thevault.service.dto.DepositResponseObject;
import com.revature.thevault.service.dto.TransactionObject;
import com.revature.thevault.service.dto.WithdrawResponseObject;
import com.revature.thevault.service.interfaces.TransactionServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service("transactionService")
public class TransactionService implements TransactionServiceInterface {

    @Autowired
    private DepositService depositService;

    @Autowired
    private WithdrawService withdrawService;

    @Override
    public GetResponse getTransactionHistory(Integer accountId) {
        GetResponse deposits = depositService.getAllUserDeposits(accountId);
        GetResponse withdrawals = withdrawService.getAllUserWithdrawals(accountId);
        List<TransactionObject> transactionObjects = new ArrayList<>();
        deposits.getGotObject().forEach(deposit -> transactionObjects.add(convertDepositToTransactionObject((DepositResponseObject) deposit)));
        withdrawals.getGotObject().forEach(withdrawal -> transactionObjects.add(convertWithdrawToTransactionObject((WithdrawResponseObject) withdrawal)));
        Comparator<TransactionObject> byDate = Comparator.comparing(TransactionObject::getDate);
        transactionObjects.sort(byDate);
        return GetResponse.builder()
                .success(true)
                .gotObject(transactionObjects)
                .build();
    }

    private TransactionObject convertWithdrawToTransactionObject(WithdrawResponseObject withdrawals) {
        return new TransactionObject(
                withdrawals.getWithdrawId(),
                "Withdraw",
                withdrawals.getRequestType(),
                withdrawals.getReference(),
                withdrawals.getDateWithdraw(),
                withdrawals.getAmount()
        );
    }

    private TransactionObject convertDepositToTransactionObject(DepositResponseObject deposit) {
        return new TransactionObject(
                deposit.getDepositId(),
                "Deposit",
                deposit.getDepositType(),
                deposit.getReference(),
                deposit.getDateDeposit(),
                deposit.getAmount()
        );
    }
}
