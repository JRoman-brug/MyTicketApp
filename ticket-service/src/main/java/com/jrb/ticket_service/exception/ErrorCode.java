package com.jrb.ticket_service.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    HALL_NOT_FOUND("ERR-001", "The hall no exists");

    private String code;
    private String message;
}
