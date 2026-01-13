package com.jrb.ticket_service.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jrb.ticket_service.dtos.ErrorDTO;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(HallNotFound.class)
    public ResponseEntity<ErrorDTO> hallNotFoundHaldler(HallNotFound ex) {
        ErrorDTO response = new ErrorDTO(ex.getMessage(), new Date());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
