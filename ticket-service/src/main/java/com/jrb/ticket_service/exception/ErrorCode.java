package com.jrb.ticket_service.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    HALL_NOT_FOUND("ERR-001", "The hall no exists"),
    SEAT_NOT_FOUND("ERR-002", "The seat no exists"),
    SEAT_RESERVED("ERR-003", "The seat is reserved"),
    MOVIE_NOT_FOUND("ERR-004", "The seat no exist");

    private String code;
    private String message;
}
