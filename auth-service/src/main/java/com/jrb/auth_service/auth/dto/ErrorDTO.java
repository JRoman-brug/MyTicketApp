package com.jrb.auth_service.auth.dto;

import java.util.Date;

public record ErrorDTO(
                Date timespan,
                String message

) {

}
