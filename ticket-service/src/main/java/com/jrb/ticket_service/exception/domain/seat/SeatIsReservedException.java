package com.jrb.ticket_service.exception.domain.seat;

import org.springframework.http.HttpStatus;

import com.jrb.ticket_service.exception.base.BusinessException;
import com.jrb.ticket_service.exception.base.ErrorCode;

public class SeatIsReservedException extends BusinessException {

    public SeatIsReservedException(Long id) {
        super("Seat is already reserved for this showtime, ID: " + id, ErrorCode.SEAT_RESERVED, HttpStatus.CONFLICT);
    }

}
