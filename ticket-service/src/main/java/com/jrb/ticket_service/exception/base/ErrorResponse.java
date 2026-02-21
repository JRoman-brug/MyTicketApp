package com.jrb.ticket_service.exception.base;

import java.time.LocalDateTime;
import java.util.Map;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Standard error response structure for the application.
 * Used by the global exception handler to provide consistent error information
 * to clients.
 * 
 * @param core      error code identifying the type of error
 * @param message   descriptive error message explaining what went wrong
 * @param timestamp timestamp when the error occurred
 * @param details   additional context-specific details about the error (e.g.,
 *                  validation errors)
 */
@Schema(name = "ErrorResponse", description = "Standard error response structure")
public record ErrorResponse(
        @Schema(description = "Error code identifying the type of error", example = "VALIDATION_ERROR") String core,
        @Schema(description = "Descriptive error message explaining what went wrong", example = "The request contains invalid data.") String message,
        @Schema(description = "Timestamp when the error occurred", example = "2026-02-20T10:30:00") LocalDateTime timestamp,
        @Schema(description = "Additional context-specific details about the error", example = "{\"name\": \"must not be null\"}", nullable = true) Map<String, String> details

) {

}
