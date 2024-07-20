package com.sm.carwashmonitor.exception;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleEntityNotFoundException(EntityNotFoundException entityNotFoundException){
        ErrorDetails errorDetails = prepareErrorDetails(entityNotFoundException);
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = GenericValidationException.class)
    public ResponseEntity<ErrorDetails> handleGenericValidationException(GenericValidationException genericValidationException){
        ErrorDetails errorDetails = prepareErrorDetails(genericValidationException);
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    public ErrorDetails prepareErrorDetails(Exception exception){
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setTimeStamp(LocalDateTime.now());
        errorDetails.setMessage(exception.getMessage());
        return errorDetails;
    }
}
