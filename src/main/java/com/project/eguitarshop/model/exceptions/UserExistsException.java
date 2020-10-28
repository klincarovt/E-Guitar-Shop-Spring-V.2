package com.project.eguitarshop.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserExistsException extends RuntimeException
{

    public UserExistsException(String userId) {
        super(String.format("User with username: %s already exists!", userId));
    }
}


