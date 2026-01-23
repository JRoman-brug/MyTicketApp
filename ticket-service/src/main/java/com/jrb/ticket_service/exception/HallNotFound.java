package com.jrb.ticket_service.exception;

import com.jrb.ticket_service.exception.base.ErrorCode;

public class HallNotFound extends RuntimeException {
    private final ErrorCode errorCode;

    public HallNotFound(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

}
