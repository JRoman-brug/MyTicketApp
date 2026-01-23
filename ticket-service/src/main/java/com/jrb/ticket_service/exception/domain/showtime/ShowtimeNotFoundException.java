package com.jrb.ticket_service.exception.domain.showtime;

import org.springframework.http.HttpStatus;

import com.jrb.ticket_service.exception.base.BusinessException;
import com.jrb.ticket_service.exception.base.ErrorCode;

public class ShowtimeNotFoundException extends BusinessException {

    public ShowtimeNotFoundException(Long id) {
        super("Showtime not found, ID:" + id, ErrorCode.SHOWTIME_NOT_FOUND, HttpStatus.NOT_FOUND);
    }
}
