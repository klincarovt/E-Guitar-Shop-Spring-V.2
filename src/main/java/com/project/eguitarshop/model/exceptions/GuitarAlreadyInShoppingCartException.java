package com.project.eguitarshop.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.PRECONDITION_FAILED)
public class GuitarAlreadyInShoppingCartException extends RuntimeException{
    public GuitarAlreadyInShoppingCartException(String name) {
        super(String.format("Guitar %s is already in ative shopping cart",name));
    }
}
