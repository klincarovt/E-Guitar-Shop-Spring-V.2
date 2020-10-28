package com.project.eguitarshop.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CartItemNotFound extends RuntimeException{
    public CartItemNotFound(Long id) {
        super(String.format("Cart item with id %d is not found!",id));
    }

}
