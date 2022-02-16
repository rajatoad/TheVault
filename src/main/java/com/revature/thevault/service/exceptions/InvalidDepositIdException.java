package com.revature.thevault.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidDepositIdException extends ResponseStatusException {
    public InvalidDepositIdException(HttpStatus status, String message) {
        super(status, message);
    }
}
