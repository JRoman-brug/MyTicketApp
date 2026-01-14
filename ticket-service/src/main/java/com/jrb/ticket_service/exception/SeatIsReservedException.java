package com.jrb.ticket_service.exception;

public class SeatIsReservedException extends RuntimeException {
    ErrorCode errorCode;

    public SeatIsReservedException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
