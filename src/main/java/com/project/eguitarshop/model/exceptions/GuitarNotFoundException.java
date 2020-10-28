package com.project.eguitarshop.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class GuitarNotFoundException extends RuntimeException {
    public GuitarNotFoundException(Long id) {
        super(String.format("Book with %d id is not found!",id));
    }
}
