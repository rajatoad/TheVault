package com.revature.thevault.presentation.model.request;


import com.revature.thevault.presentation.model.GenericLoginRequest;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
public class ResetPasswordRequest {
    String username;
    String email;

}
