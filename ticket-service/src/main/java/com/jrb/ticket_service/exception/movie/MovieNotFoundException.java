package com.jrb.ticket_service.exception.movie;

import com.jrb.ticket_service.exception.base.ErrorCode;

public class MovieNotFoundException extends RuntimeException {
    private final ErrorCode errorCode;

    public MovieNotFoundException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
