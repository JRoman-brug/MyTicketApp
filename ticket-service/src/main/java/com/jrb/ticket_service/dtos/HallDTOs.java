package com.jrb.ticket_service.dtos;

import java.util.List;

import jakarta.validation.constraints.NotNull;

public class HallDTOs {
        private HallDTOs() {
        }

        public record CreateRequest(
                        @NotNull String label,
                        @NotNull int rows,
                        @NotNull int columns,
                        @NotNull List<String> rowLabels,
                        @NotNull List<String> columnLabels,
                        @NotNull List<List<String>> schema) {
        }

        public record Response(
                        Long id,
                        String name,
                        int totalRows,
                        int totalColumns,
                        List<SeatDTOs.Summary> seats) {

        }

        public record Summary(
                        Long id,
                        String name) {
        }
}
