package com.revature.thevault.repository.entity;

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
