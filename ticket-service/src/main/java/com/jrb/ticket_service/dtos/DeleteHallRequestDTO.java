package com.jrb.ticket_service.dtos;

import jakarta.validation.constraints.NotNull;

public record DeleteHallRequestDTO(
        @NotNull Long id) {

}
