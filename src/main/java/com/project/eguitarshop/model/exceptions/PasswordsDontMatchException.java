package com.project.eguitarshop.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class PasswordsDontMatchException extends RuntimeException {
    public PasswordsDontMatchException() {
        super("Passwords dont match!!!");
    }
}
