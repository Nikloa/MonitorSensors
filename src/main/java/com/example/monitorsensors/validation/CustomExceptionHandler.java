package com.example.monitorsensors.validation;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.*;

@ControllerAdvice
public class CustomExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<?> handleEmptyFieldException(InvalidFormatException e) {
        return new ResponseEntity<>(e.getOriginalMessage(), HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<?> getConstraintViolationException(TransactionSystemException e) {
        Throwable throwable = e.getCause();
        while ((throwable != null) && !(throwable instanceof ConstraintViolationException)) {
            throwable = throwable.getCause();
        }
        if (throwable != null) {
            return new ResponseEntity<>(throwable.getMessage(), HttpStatus.BAD_REQUEST);
        } else {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
