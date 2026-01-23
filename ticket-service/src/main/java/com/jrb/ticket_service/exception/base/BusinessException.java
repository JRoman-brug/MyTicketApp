package com.jrb.ticket_service.exception.base;

import org.springframework.http.HttpStatus;

public abstract class BusinessException extends RuntimeException {
    private final ErrorCode errorCode;
    private final HttpStatus status;

    protected BusinessException(String message, ErrorCode errorCode, HttpStatus status) {
        super(message);
        this.errorCode = errorCode;
        this.status = status;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
