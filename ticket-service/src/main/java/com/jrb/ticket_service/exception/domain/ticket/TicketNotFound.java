package com.jrb.ticket_service.exception.domain.ticket;

import org.springframework.http.HttpStatus;

import com.jrb.ticket_service.exception.base.BusinessException;
import com.jrb.ticket_service.exception.base.ErrorCode;

public class TicketNotFound extends BusinessException {
    public TicketNotFound(Long id) {
        super("The ticket with ID " + id + " not found", ErrorCode.TICKET_NOT_FOUND, HttpStatus.NOT_FOUND);
    }
}
