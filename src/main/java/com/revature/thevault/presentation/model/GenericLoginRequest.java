package com.revature.thevault.presentation.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class GenericLoginRequest {
    String username;
    String password;
}