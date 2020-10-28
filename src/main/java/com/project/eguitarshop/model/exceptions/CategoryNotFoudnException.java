package com.project.eguitarshop.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CategoryNotFoudnException extends RuntimeException {
    public CategoryNotFoudnException(Long id) {
        super(String.format("Category with id %d is not found!",id));
    }
}
