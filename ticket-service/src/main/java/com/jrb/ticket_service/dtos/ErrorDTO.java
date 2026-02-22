package com.jrb.ticket_service.dtos;

import java.util.Date;

public record ErrorDTO(
        String message,
        Date timespan) {

}
