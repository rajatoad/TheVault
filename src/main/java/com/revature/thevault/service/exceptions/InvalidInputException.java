package com.revature.thevault.service.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidInputException extends ResponseStatusException {
    public InvalidInputException(String message) { super(HttpStatus.BAD_REQUEST, message);}

    @Override
    public HttpHeaders getResponseHeaders() { return super.getResponseHeaders();}

}
