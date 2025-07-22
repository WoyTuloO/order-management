package com.example.customerorder.config.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NonPositiveAmountException extends RuntimeException {
    public NonPositiveAmountException(String message) {
        super(message);
    }
}
