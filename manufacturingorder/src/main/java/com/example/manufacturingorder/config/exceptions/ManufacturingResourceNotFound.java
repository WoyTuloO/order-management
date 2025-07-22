package com.example.manufacturingorder.config.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ManufacturingResourceNotFound extends RuntimeException {
    public ManufacturingResourceNotFound(String message) {
        super(message);
    }
}
