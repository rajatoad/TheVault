package com.revature.thevault.presentation.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferRequest {
    int ownerAccountId;
    int receiverAccountId;
    int amount;
}
