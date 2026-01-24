package com.jrb.ticket_service.exception.domain.ticket;

import org.springframework.http.HttpStatus;

import com.jrb.ticket_service.exception.base.BusinessException;
import com.jrb.ticket_service.exception.base.ErrorCode;

public class SeatNotBelongingToHallException extends BusinessException {
    public SeatNotBelongingToHallException(Long hallId, Long seatId) {
        super("Seat " + seatId + " not belongs to hall " + hallId, ErrorCode.SEAT_NOT_BELONGS_TO_HALL,
                HttpStatus.BAD_REQUEST);
    }
}
