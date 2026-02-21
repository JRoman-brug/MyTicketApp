package com.jrb.ticket_service.exception.handler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.jrb.ticket_service.exception.base.BusinessException;
import com.jrb.ticket_service.exception.base.ErrorResponse;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;

/**
 * Global exception handler for the Ticket Service.
 * Handles all exceptions thrown by the application and returns standardized
 * error responses.
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

        /**
         * Handles business exceptions thrown by the application.
         * 
         * @param ex the business exception
         * @return error response with business error details
         */
        @ExceptionHandler(BusinessException.class)
        @Hidden
        public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException ex) {
                ErrorResponse response = new ErrorResponse(
                                ex.getErrorCode().getCode(),
                                ex.getMessage(),
                                LocalDateTime.now(),
                                null);
                return new ResponseEntity<>(response, ex.getStatus());
        }

        /**
         * Handles validation exceptions for request body parameters.
         * 
         * @param ex the validation exception
         * @return error response with validation error details
         */
        @ExceptionHandler(MethodArgumentNotValidException.class)
        @Hidden
        public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
                Map<String, String> details = new HashMap<>();
                ex.getBindingResult().getFieldErrors()
                                .forEach(error -> details.put(error.getField(), error.getDefaultMessage()));

                ErrorResponse response = new ErrorResponse(
                                "VALIDATION_ERROR",
                                "The request contains invalid data.",
                                LocalDateTime.now(),
                                details);
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        /**
         * Handles type mismatch exceptions for path/query parameters.
         * 
         * @param ex the type mismatch exception
         * @return error response with type mismatch details
         */
        @ExceptionHandler(MethodArgumentTypeMismatchException.class)
        @Hidden
        public ResponseEntity<ErrorResponse> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
                String typeName = Optional.ofNullable(ex.getRequiredType())
                                .map(Class::getSimpleName)
                                .orElse("unknown");

                String detailMsg = String.format("Parameter '%s' expects a value of type %s",
                                ex.getName(), typeName);

                ErrorResponse response = new ErrorResponse(
                                "TYPE_MISMATCH",
                                "Invalid parameter type in the URL.",
                                LocalDateTime.now(),
                                Map.of("parameter", detailMsg));
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        /**
         * Handles malformed JSON or unreadable request body exceptions.
         * 
         * @param ex the message not readable exception
         * @return error response with malformed JSON details
         */
        @ExceptionHandler(HttpMessageNotReadableException.class)
        @Hidden
        public ResponseEntity<ErrorResponse> handleReadableException(HttpMessageNotReadableException ex) {
                ErrorResponse response = new ErrorResponse(
                                "MALFORMED_JSON",
                                "The request body is unreadable or has invalid formats.",
                                LocalDateTime.now(),
                                null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        /**
         * Handles HTTP method not supported exceptions.
         * 
         * @param ex the method not supported exception
         * @return error response with method not allowed details
         */
        @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
        @Hidden
        public ResponseEntity<ErrorResponse> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
                ErrorResponse response = new ErrorResponse(
                                "METHOD_NOT_ALLOWED",
                                String.format("HTTP method %s is not supported for this endpoint.", ex.getMethod()),
                                LocalDateTime.now(),
                                null);
                return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(response);
        }

        /**
         * Handles all other unexpected exceptions.
         * 
         * @param ex the generic exception
         * @return error response with internal server error details
         */
        @ExceptionHandler(Exception.class)
        @Hidden
        public ResponseEntity<ErrorResponse> handleAllExceptions(Exception ex) {
                log.debug(ex.toString());
                ErrorResponse response = new ErrorResponse(
                                "INTERNAL_SERVER_ERROR",
                                "An unexpected error occurred. Please contact support.",
                                LocalDateTime.now(),
                                null);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
}
