package com.jrb.ticket_service.exception;

public class SeatNotFound extends RuntimeException {
    ErrorCode errorCode;

    public SeatNotFound(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
