package com.jrb.ticket_service.exception;

public class SeatNotFound extends RuntimeException {
    private final ErrorCode errorCode;

    public SeatNotFound(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
