package com.revature.thevault.repository.dao.presentation.model.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProfileResponse {
    boolean success;
    String username;
    String firstName;
    String lastName;
    String email;
}


