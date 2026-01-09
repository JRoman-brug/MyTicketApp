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
import com.jrb.auth_service.auth.exceptions.MissingAuthToken;
import com.jrb.auth_service.user.exception.UserNotFoundException;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

@ControllerAdvice
public class GlobalExceptionHandler {
    static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(exception = EmailAlreadyUserException.class)
    public ResponseEntity<ErrorDTO> handlerEmailDuplicated(EmailAlreadyUserException ex) {
        logger.error(ex.getMessage());
        ErrorDTO response = new ErrorDTO(new Date(), ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(exception = UserNotFoundException.class)
    public ResponseEntity<ErrorDTO> handlerUserNotFound(UserNotFoundException ex) {
        ErrorDTO response = new ErrorDTO(new Date(), ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(exception = MissingAuthToken.class)
    public ResponseEntity<ErrorDTO> handlerMissingToken(MissingAuthToken ex) {
        ErrorDTO response = new ErrorDTO(new Date(), ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // UnsupportedJwtException - if the jwt argument does not represent a signed
    // Claims JWT
    // JwtException - if the jwt string cannot be parsed or validated as required.
    // IllegalArgumentException
    @ExceptionHandler(exception = MalformedJwtException.class)
    public ResponseEntity<ErrorDTO> handlerMalformedJWTException(MalformedJwtException ex) {
        ErrorDTO response = new ErrorDTO(new Date(), "Invalid JWT.");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(exception = ExpiredJwtException.class)
    public ResponseEntity<ErrorDTO> handlerExpiredJWTException(ExpiredJwtException ex) {
        ErrorDTO response = new ErrorDTO(new Date(), "JWT expired");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
