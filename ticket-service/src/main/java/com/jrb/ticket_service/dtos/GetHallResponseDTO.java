package com.jrb.ticket_service.dtos;

import java.util.List;

public record GetHallResponseDTO(
        String name,
        int totalColumns,
        int totalRows,
        List<SeatDTO> seats) {

}
