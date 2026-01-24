package com.jrb.ticket_service.exception.domain.ticket;

import org.springframework.http.HttpStatus;

import com.jrb.ticket_service.exception.base.BusinessException;
import com.jrb.ticket_service.exception.base.ErrorCode;

public class SeatAlreadyOcuppiedException extends BusinessException {
    public SeatAlreadyOcuppiedException(Long seatId, Long showtimeId) {
        super("The seat " + seatId + " from the showtime " + showtimeId + " is already ocuppied",
                ErrorCode.SEAT_IS_ALREADY_OCUPPIED, HttpStatus.CONFLICT);
    }
}
