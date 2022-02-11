package com.revature.thevault.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidAccountIdException extends ResponseStatusException {
    public InvalidAccountIdException(HttpStatus status, String reason) {
        super(status, reason);
    }
}
