package com.jrb.ticket_service.dtos;

import java.util.List;

public record CreateHallDTO(
        String label,
        int columns,
        int rows,
        List<String> columnLabels,
        List<String> rowLabels,
        List<List<String>> schema) {

}
