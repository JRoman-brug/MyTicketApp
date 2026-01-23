package com.jrb.ticket_service.exception.domain.hall;

import org.springframework.http.HttpStatus;

import com.jrb.ticket_service.exception.base.BusinessException;
import com.jrb.ticket_service.exception.base.ErrorCode;

public class HallNotFoundException extends BusinessException {
    public HallNotFoundException(Long id) {
        super("Hall not found with ID: " + id,
                ErrorCode.HALL_NOT_FOUND,
                HttpStatus.NOT_FOUND);
    }

}
