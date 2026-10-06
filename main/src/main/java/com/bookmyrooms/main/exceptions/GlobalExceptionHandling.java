package com.bookmyrooms.main.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandling {

    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<?> handleDuplicate(DuplicateException ex, HttpServletRequest request){
        ApiError err = new ApiError(
                HttpStatus.NOT_ACCEPTABLE, ex.getMessage(), request.getRequestURI()
        );
        return new ResponseEntity<>(err, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public  ResponseEntity<?> handleResourceNotFound(ResourceNotFoundException ex, HttpServletRequest request){
        ApiError err = new ApiError(
                HttpStatus.NOT_FOUND, ex.getMessage(), request.getRequestURI()
        );

        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }
}


