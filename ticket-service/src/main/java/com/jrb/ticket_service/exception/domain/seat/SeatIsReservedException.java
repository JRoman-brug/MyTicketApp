package com.jrb.ticket_service.exception.domain.seat;

import com.jrb.ticket_service.exception.base.ErrorCode;

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
