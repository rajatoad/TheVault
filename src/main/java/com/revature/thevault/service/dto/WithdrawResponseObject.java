package com.revature.thevault.service.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WithdrawResponseObject {
    int withdrawId;
    int accountId;
    String requestType;
    String requestStatus;
    String reference;
    LocalDate dateWithdraw;
    float amount;
}
