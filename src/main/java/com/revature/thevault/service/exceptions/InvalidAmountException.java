package com.revature.thevault.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidAmountException extends ResponseStatusException {
    public InvalidAmountException(HttpStatus status, String reason) {
        super(status, reason);
    }
}
