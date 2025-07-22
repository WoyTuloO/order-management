package com.example.itsolutionspp.config;

import com.example.customerorder.config.exceptions.CustomerResourceNotFound;
import com.example.customerorder.config.exceptions.InvalidIdException;
import com.example.customerorder.config.exceptions.NonPositiveAmountException;
import com.example.manufacturingorder.config.exceptions.CannotUpdateCompletedOrderException;
import com.example.manufacturingorder.config.exceptions.ManufacturingResourceNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ManufacturingResourceNotFound.class)
    public ResponseEntity<?> handleResourceNotFoundException(ManufacturingResourceNotFound ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", ex.getMessage()));
    }

    @ExceptionHandler(CustomerResourceNotFound.class)
    public ResponseEntity<?> handleResourceNotFoundException(CustomerResourceNotFound ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", ex.getMessage()));
    }

    @ExceptionHandler(NonPositiveAmountException.class)
    public ResponseEntity<?> handleNonPositiveAmountException(NonPositiveAmountException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("error", ex.getMessage()));
    }

    @ExceptionHandler(CannotUpdateCompletedOrderException.class)
    public ResponseEntity<?> handleCannotUpdateCompletedOrderException(CannotUpdateCompletedOrderException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Map.of("error", "Order cannot be updated as it is already completed."));
    }


    @ExceptionHandler(InvalidIdException.class)
    public ResponseEntity<?> handleInvalidIdException(InvalidIdException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("error", ex.getMessage()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        Throwable rootCause = ex.getRootCause();
        if (rootCause instanceof NonPositiveAmountException) {
            return handleNonPositiveAmountException((NonPositiveAmountException) rootCause);
        }

        if (rootCause instanceof InvalidIdException) {
            return handleInvalidIdException((InvalidIdException) rootCause);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("error", "Invalid request body"));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleOtherExceptions(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("error", "Unexpected error occurred."));
    }

}
