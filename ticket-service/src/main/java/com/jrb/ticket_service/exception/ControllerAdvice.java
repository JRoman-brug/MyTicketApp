package com.jrb.ticket_service.exception;

import java.util.Date;

import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
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

    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    public ResponseEntity<ErrorDTO> missingAttributes(InvalidDataAccessApiUsageException ex) {
        ErrorDTO response = new ErrorDTO("Error body attributes", new Date());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorDTO> missmatchType(HttpMessageNotReadableException ex) {
        ErrorDTO response = new ErrorDTO("Error attributes type", new Date());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
