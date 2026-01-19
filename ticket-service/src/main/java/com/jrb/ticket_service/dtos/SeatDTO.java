package com.jrb.ticket_service.dtos;

public record SeatDTO(
        long seatId,
        int row,
        int column,
        String label) {

}
