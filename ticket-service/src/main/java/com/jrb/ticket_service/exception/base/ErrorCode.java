package com.jrb.ticket_service.exception.base;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "Error codes used throughout the application")
public enum ErrorCode {
    // Business Logic Errors
    @Schema(description = "Hall not found in the database")
    HALL_NOT_FOUND("ERR-001", "The hall does not exist"),

    @Schema(description = "Seat not found in the database")
    SEAT_NOT_FOUND("ERR-002", "The seat does not exist"),

    @Schema(description = "Seat is already reserved by another user")
    SEAT_RESERVED("ERR-003", "The seat is reserved"),

    @Schema(description = "Movie not found in the database")
    MOVIE_NOT_FOUND("ERR-004", "The movie does not exist"),

    @Schema(description = "Showtime not found in the database")
    SHOWTIME_NOT_FOUND("ERR-005", "The showtime does not exist"),

    @Schema(description = "Showtime conflicts with another showtime in the same hall")
    SHOWTIME_SCHEDULE_CONFLICT("ERR-006", "The showtime has a collision with another showtime"),

    @Schema(description = "Seat does not belong to the specified hall")
    SEAT_NOT_BELONGS_TO_HALL("ERR-007", "The seat does not belong to this hall"),

    @Schema(description = "Seat is already occupied for the selected showtime")
    SEAT_IS_ALREADY_OCUPPIED("ERR-008", "The seat is already occupied"),

    @Schema(description = "Ticket not found in the database")
    TICKET_NOT_FOUND("ERR-009", "The ticket does not exist"),

    @Schema(description = "Ticket not found in the database")
    EXAMPLE_NOT_FOUND("ERR-010", "this is a example"),

    // System and Validation Errors
    @Schema(description = "Request validation failed")
    VALIDATION_ERROR("VALIDATION_ERROR", "The request contains invalid data"),

    @Schema(description = "Invalid parameter type")
    TYPE_MISMATCH("TYPE_MISMATCH", "Invalid parameter type in the URL"),

    @Schema(description = "Invalid JSON format")
    MALFORMED_JSON("MALFORMED_JSON", "The request body is unreadable or has invalid formats"),

    @Schema(description = "HTTP method not supported")
    METHOD_NOT_ALLOWED("METHOD_NOT_ALLOWED", "HTTP method is not supported for this endpoint"),

    @Schema(description = "Unexpected server error")
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", "An unexpected error occurred");

    @Schema(description = "Unique error code identifier", example = "ERR-001")
    private String code;

    @Schema(description = "Human-readable error message", example = "The hall does not exist")
    private String message;
}
