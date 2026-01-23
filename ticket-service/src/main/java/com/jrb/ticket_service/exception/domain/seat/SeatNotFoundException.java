package com.jrb.ticket_service.exception.domain.seat;

import org.springframework.http.HttpStatus;

import com.jrb.ticket_service.exception.base.BusinessException;
import com.jrb.ticket_service.exception.base.ErrorCode;

public class SeatNotFoundException extends BusinessException {

    public SeatNotFoundException(Long id) {
        super("The seat not found, ID: " + id, ErrorCode.SEAT_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

}
