package com.abdul.hellospring.controller.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.abdul.hellospring.model.error.ErrorMessage;
import com.abdul.hellospring.model.exception.ResourceNotFoundException;
import com.abdul.hellospring.service.exception.InvalidProductException;

@ControllerAdvice
public class RestExceptionHandler {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException exception){
        var error = ErrorMessage.builder("Not Found")
                                .message(exception.getMessage())
                                .statusCode(HttpStatus.NOT_FOUND.value())
                                .build();
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidProductException.class)
    public ResponseEntity<?> handleInvalidInputException(InvalidProductException exception){
        var error = ErrorMessage.builder("Invalid request")
                                .message(exception.getMessage())
                                .statusCode(HttpStatus.BAD_REQUEST.value())
                                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
