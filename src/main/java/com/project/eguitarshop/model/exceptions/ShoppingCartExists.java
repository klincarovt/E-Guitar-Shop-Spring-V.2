package com.project.eguitarshop.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
public class ShoppingCartExists extends RuntimeException{
    public ShoppingCartExists() {
        super(String.format("Shopping cart already exits"));
    }
}
