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
public class TransactionObject {
    int transactionId;
    String transactionType;
    String transactionDetail;
    String transactionReference;
    LocalDate date;
    float amount;


}
