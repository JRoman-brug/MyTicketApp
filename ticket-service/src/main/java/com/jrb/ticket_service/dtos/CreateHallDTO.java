package com.jrb.ticket_service.dtos;

import java.util.List;

public record CreateHallDTO(
                String label,
                int rows,
                int columns,
                List<String> rowLabels,
                List<String> columnLabels,
                List<List<String>> schema) {

}
