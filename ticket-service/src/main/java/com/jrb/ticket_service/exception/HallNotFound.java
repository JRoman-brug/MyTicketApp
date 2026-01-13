package com.jrb.ticket_service.exception;

public class HallNotFound extends RuntimeException {
    private final ErrorCode errorCode;

    public HallNotFound(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

}
