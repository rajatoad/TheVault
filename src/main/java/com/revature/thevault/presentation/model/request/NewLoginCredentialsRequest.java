package com.revature.thevault.presentation.model.request;

import com.revature.thevault.presentation.model.GenericLoginRequest;
import lombok.*;
import lombok.experimental.FieldDefaults;

@EqualsAndHashCode(callSuper = true)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NewLoginCredentialsRequest extends GenericLoginRequest {
}
