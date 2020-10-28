package com.project.eguitarshop.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class OutOfStockException extends RuntimeException {
    public OutOfStockException() {
        super(String.format("This item is no longer in stock"));
    }
}
