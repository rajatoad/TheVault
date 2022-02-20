package com.revature.thevault.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidWithdrawIdRequest extends ResponseStatusException {
    public InvalidWithdrawIdRequest(HttpStatus status, String message) {
        super(status, message);
    }
}
