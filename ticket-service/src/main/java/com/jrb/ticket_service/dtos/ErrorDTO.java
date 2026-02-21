package com.jrb.ticket_service.dtos;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Data Transfer Object for error responses.
 * Provides standardized error information to clients.
 * 
 * @param message  descriptive error message explaining what went wrong
 * @param timespan timestamp when the error occurred
 */
@Schema(name = "ErrorResponse", description = "Error response DTO")
public record ErrorDTO(
                @Schema(description = "Descriptive error message", example = "Resource not found") String message,
                @Schema(description = "Timestamp when the error occurred", example = "2026-02-20T10:30:00.000Z") Date timespan) {

}
