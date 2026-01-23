package com.jrb.ticket_service.dtos;

import com.jrb.ticket_service.entity.enums.TicketStatus;

import jakarta.validation.constraints.NotNull;

public class TicketDTOs {
    private TicketDTOs() {
    }

    public record ReservationRequest(
            @NotNull Long seatId,
            @NotNull Long showtimeId) {
    }

    public record Response(
            Long id,
            String seatLabel,
            String movieName,
            String hallName,
            Long showtimeId,
            Long userId) {
    }

    public record Summary(
            Long id,
            TicketStatus status,
            Long userId) {

    }

}
