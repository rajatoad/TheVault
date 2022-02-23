package com.revature.thevault.service.classes;

import com.revature.thevault.presentation.model.response.builder.GetResponse;
import com.revature.thevault.service.dto.DepositResponseObject;
import com.revature.thevault.service.dto.TransactionObject;
import com.revature.thevault.service.dto.WithdrawResponseObject;
import com.revature.thevault.service.exceptions.InvalidAccountIdException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)

class TransactionServiceTest {

    @Autowired
    private TransactionService transactionService;

    @MockBean
    private DepositService depositService;

    @MockBean
    private WithdrawService withdrawService;

    private DepositResponseObject depositResponseObject1, depositResponseObject2;
    private WithdrawResponseObject withdrawResponseObject1, withdrawResponseObject2;

    private TransactionObject transactionObject1, transactionObject2, transactionObject3, transactionObject4;

    private int accountId = 1;


    @BeforeAll
    void beforeAllSetup(){
        MockitoAnnotations.openMocks(this);

        depositResponseObject1 = new DepositResponseObject(1, accountId, "Cash", "reference", LocalDate.of(2033, 1, 1), 100F);
        depositResponseObject2 = new DepositResponseObject(2, accountId, "Cash", "reference", LocalDate.of(1999, 10, 10), 100F);

        withdrawResponseObject1 = new WithdrawResponseObject(1, accountId, "Retail", "Pending", "reference", LocalDate.of(2000, 1, 1), 10F);
        withdrawResponseObject2 = new WithdrawResponseObject(2, accountId, "Retail", "Pending", "reference", LocalDate.of(2003, 1, 1), 10F);

        transactionObject1 = new TransactionObject(
                depositResponseObject1.getDepositId(),
                "Deposit",
                depositResponseObject1.getDepositType(),
                depositResponseObject1.getReference(),
                depositResponseObject1.getDateDeposit(),
                depositResponseObject1.getAmount()
        );

        transactionObject2 = new TransactionObject(
                withdrawResponseObject2.getWithdrawId(),
                "Withdraw",
                withdrawResponseObject2.getRequestType(),
                withdrawResponseObject2.getReference(),
                withdrawResponseObject2.getDateWithdraw(),
                withdrawResponseObject2.getAmount()
        );

        transactionObject3 = new TransactionObject(
                withdrawResponseObject1.getWithdrawId(),
                "Withdraw",
                withdrawResponseObject1.getRequestType(),
                withdrawResponseObject1.getReference(),
                withdrawResponseObject1.getDateWithdraw(),
                withdrawResponseObject1.getAmount()
        );

        transactionObject4 = new TransactionObject(
                depositResponseObject2.getDepositId(),
                "Deposit",
                depositResponseObject2.getDepositType(),
                depositResponseObject2.getReference(),
                depositResponseObject2.getDateDeposit(),
                depositResponseObject2.getAmount()
        );
    }

    @BeforeEach
    void beforeEachSetup(){

        Mockito.when(depositService.getAllUserDeposits(accountId))
                .thenReturn(
                        GetResponse.builder()
                                .success(true)
                                .gotObject(Arrays.asList(depositResponseObject1, depositResponseObject2))
                                .build());

        Mockito.when(withdrawService.getAllUserWithdrawals(accountId))
                .thenReturn(
                        GetResponse.builder()
                                .success(true)
                                .gotObject(Arrays.asList(withdrawResponseObject1, withdrawResponseObject2))
                                .build()
                );
    }

    @Test
    void getTransactionHistoryOrderedByDate() {
        GetResponse sortedGetResponse = GetResponse.builder()
                .success(true)
                .gotObject(Arrays.asList(transactionObject1, transactionObject2, transactionObject3, transactionObject4))
                .build();
        assertEquals(sortedGetResponse, transactionService.getTransactionHistory(accountId));
        List<TransactionObject> transactionObjectList = (List<TransactionObject>) transactionService.getTransactionHistory(accountId).getGotObject();
        assertTrue(transactionObjectList.get(0).getDate().compareTo(transactionObjectList.get(1).getDate()) > 0,"The dates are in order of earliest to oldest");
    }

    @Test
    void getTransactionHistoryNoTransactions(){
        GetResponse getResponse = GetResponse.builder()
                .success(true)
                .gotObject(Collections.EMPTY_LIST)
                .build();
        Mockito.when(depositService.getAllUserDeposits(2)).thenReturn(GetResponse.builder().success(true).gotObject(Collections.EMPTY_LIST).build());
        Mockito.when(withdrawService.getAllUserWithdrawals(2)).thenReturn(GetResponse.builder().success(true).gotObject(Collections.EMPTY_LIST).build());

        assertEquals(getResponse, transactionService.getTransactionHistory(2));
    }

    @Test
    void getTransactionHistoryInvalidAccountId(){
        assertThrows(InvalidAccountIdException.class, () -> transactionService.getTransactionHistory(-1));
    }
}