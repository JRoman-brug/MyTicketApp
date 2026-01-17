package com.jrb.ticket_service.dtos;

import java.util.List;

public record GetHallResponseDTO(
                String name,
                int totalRows,
                int totalColumns,
                List<SeatDTO> seats) {

}
