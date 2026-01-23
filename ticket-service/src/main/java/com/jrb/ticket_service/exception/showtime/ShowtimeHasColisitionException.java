package com.jrb.ticket_service.exception.showtime;

import com.jrb.ticket_service.exception.base.ErrorCode;

public class ShowtimeHasColisitionException extends RuntimeException {
    private final ErrorCode errorCode;

    public ShowtimeHasColisitionException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
