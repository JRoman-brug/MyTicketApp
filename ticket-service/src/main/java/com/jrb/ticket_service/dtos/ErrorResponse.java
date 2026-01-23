package com.jrb.ticket_service.dtos;

import java.time.LocalDateTime;
import java.util.Map;

public record ErrorResponse(
        String core,
        String message,
        LocalDateTime timestamp,
        Map<String, String> details

) {

}
