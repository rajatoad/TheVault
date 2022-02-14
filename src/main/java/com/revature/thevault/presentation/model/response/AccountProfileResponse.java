package com.revature.thevault.presentation.model.response;

import com.revature.thevault.repository.entity.AccountProfileEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountProfileResponse {
    boolean success;
    AccountProfileEntity accountProfileEntity;
}
