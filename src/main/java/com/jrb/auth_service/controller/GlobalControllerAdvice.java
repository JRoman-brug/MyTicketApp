package com.jrb.auth_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jrb.auth_service.exception.UserNotFoundException;

@RestControllerAdvice
public class GlobalControllerAdvice {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> notFoundUser(UserNotFoundException ex) {
        return ResponseEntity.internalServerError().body(ex.getMessage());
    }
}