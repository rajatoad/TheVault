package com.revature.thevault.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidRequestException extends ResponseStatusException {
    public InvalidRequestException(HttpStatus httpStatus, String message) {
        super(httpStatus, message);
    }
}
