package com.revature.thevault.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidAccountTypeException extends ResponseStatusException {
    public InvalidAccountTypeException(HttpStatus status, String reason) {
        super(status, reason);
    }
}
