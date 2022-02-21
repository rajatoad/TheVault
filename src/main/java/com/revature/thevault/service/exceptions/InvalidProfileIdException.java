package com.revature.thevault.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidProfileIdException extends ResponseStatusException {
    public InvalidProfileIdException(HttpStatus status, String s) {
        super(status, s);
    }
}
