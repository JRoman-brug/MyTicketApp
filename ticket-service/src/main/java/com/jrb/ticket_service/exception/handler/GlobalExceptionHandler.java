package com.jrb.ticket_service.exception.handler;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jrb.ticket_service.dtos.ErrorDTO;
import com.jrb.ticket_service.exception.base.BusinessException;
import com.jrb.ticket_service.exception.base.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException ex) {
        ErrorResponse response = new ErrorResponse(
                ex.getErrorCode().getCode(),
                ex.getMessage(),
                LocalDateTime.now(),
                null);
        return new ResponseEntity<>(response, ex.getStatus());
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
