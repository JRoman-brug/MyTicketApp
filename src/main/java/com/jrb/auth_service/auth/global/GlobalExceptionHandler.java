package com.jrb.auth_service.auth.global;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.jrb.auth_service.auth.dto.ErrorDTO;
import com.jrb.auth_service.auth.exceptions.EmailAlreadyUserException;

@ControllerAdvice
public class GlobalExceptionHandler {
    static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(exception = EmailAlreadyUserException.class)
    public ResponseEntity<ErrorDTO> handlerEmailDuplicated(EmailAlreadyUserException ex) {
        logger.error(ex.getMessage());
        ErrorDTO response = new ErrorDTO(new Date(), ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
