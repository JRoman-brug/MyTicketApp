package com.jrb.ticket_service.exception.base;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    HALL_NOT_FOUND("ERR-001", "The hall no exists"),
    SEAT_NOT_FOUND("ERR-002", "The seat no exists"),
    SEAT_RESERVED("ERR-003", "The seat is reserved"),
    MOVIE_NOT_FOUND("ERR-004", "The seat no exist"),
    SHOWTIME_NOT_FOUND("ERR-005", "The showtime no exist"),
    SHOWTIME_SCHEDULE_CONFLICT("ERR-006", "The showtime has a colisition with other showtime"),
    SEAT_NOT_BELONGS_TO_HALL("ERR-007", "The seat not belongs to hall"),
    SEAT_IS_ALREADY_OCUPPIED("ERR-008", "The seat is already ocuppied");

    private String code;
    private String message;
}
