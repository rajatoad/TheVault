package com.revature.thevault.presentation.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewLoginCredentialsRequest {

    String username;
    String password;
    String email;
}
