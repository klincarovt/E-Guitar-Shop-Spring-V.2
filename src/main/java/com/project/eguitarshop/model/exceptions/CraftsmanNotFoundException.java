package com.project.eguitarshop.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CraftsmanNotFoundException extends RuntimeException {
    public CraftsmanNotFoundException(Long id) {
        super(String.format("The craftsman with %d id has not been foudn!",id));
    }
}
