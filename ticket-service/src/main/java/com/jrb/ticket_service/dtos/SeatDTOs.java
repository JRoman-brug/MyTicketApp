package com.jrb.ticket_service.dtos;

public class SeatDTOs {
    public record Response(
            Long id,
            int row,
            int column,
            String label,
            boolean isAvailable) {
    }

    public record Summary(Long id, String label) {
    }
}
