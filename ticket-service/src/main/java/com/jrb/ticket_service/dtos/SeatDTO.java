package com.jrb.ticket_service.dtos;

import com.jrb.ticket_service.entity.enums.TicketStatus;

public record SeatDTO(
                long seatId,
                int row,
                int column,
                String label,
                TicketStatus status) {

}
