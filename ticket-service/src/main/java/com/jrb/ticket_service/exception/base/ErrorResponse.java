package com.jrb.ticket_service.exception.base;

import java.time.LocalDateTime;
import java.util.Map;

public record ErrorResponse(
                String core,
                String message,
                LocalDateTime timestamp,
                Map<String, String> details

) {

}
