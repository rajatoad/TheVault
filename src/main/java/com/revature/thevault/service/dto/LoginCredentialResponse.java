package com.revature.thevault.service.dto;


import com.revature.thevault.presentation.model.GenericLoginRequest;
import lombok.*;
import lombok.experimental.FieldDefaults;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginCredentialResponse extends GenericLoginRequest {
    int userId;
}
