package com.jrb.ticket_service.exception;

public class SeatIsReservedException extends RuntimeException {
    private final ErrorCode errorCode;

    public SeatIsReservedException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
