package com.jrb.ticket_service.dtos;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

/**
 * Data Transfer Objects for Hall entity operations.
 * Contains records for creating halls, retrieving hall information, and hall
 * summaries.
 */
public class HallDTOs {
        private HallDTOs() {
        }

        /**
         * Request DTO for creating a new hall.
         * 
         * @param name         the name of the hall
         * @param totalRows    total number of rows in the hall
         * @param totalColumns total number of columns in the hall
         * @param rowLabels    list of labels for each row (e.g., ["A", "B", "C"])
         * @param columnLabels list of labels for each column (e.g., ["1", "2", "3"])
         * @param schema       2D list representing the seating layout structure
         */
        @Schema(name = "HallCreateRequest", description = "Request DTO for creating a new hall")
        public record CreateRequest(
                        @Schema(description = "Name of the hall", example = "Main Hall", requiredMode = Schema.RequiredMode.REQUIRED) @NotNull String name,
                        @Schema(description = "Total number of rows in the hall", example = "10", requiredMode = Schema.RequiredMode.REQUIRED) @NotNull int totalRows,
                        @Schema(description = "Total number of columns in the hall", example = "15", requiredMode = Schema.RequiredMode.REQUIRED) @NotNull int totalColumns,
                        @Schema(description = "List of labels for each row", example = "[\"A\", \"B\", \"C\"]", requiredMode = Schema.RequiredMode.REQUIRED) @NotNull List<String> rowLabels,
                        @Schema(description = "List of labels for each column", example = "[\"1\", \"2\", \"3\"]", requiredMode = Schema.RequiredMode.REQUIRED) @NotNull List<String> columnLabels,
                        @Schema(description = "2D list representing the seating layout structure", requiredMode = Schema.RequiredMode.REQUIRED) @NotNull List<List<String>> schema) {
        }

        /**
         * Response DTO containing complete hall information.
         * 
         * @param id           unique identifier of the hall
         * @param name         the name of the hall
         * @param totalRows    total number of rows in the hall
         * @param totalColumns total number of columns in the hall
         * @param seats        list of seat summaries in this hall
         */
        @Schema(name = "HallResponse", description = "Response DTO containing complete hall information")
        public record Response(
                        @Schema(description = "Unique identifier of the hall", example = "1") Long id,
                        @Schema(description = "Name of the hall", example = "Main Hall") String name,
                        @Schema(description = "Total number of rows in the hall", example = "10") int totalRows,
                        @Schema(description = "Total number of columns in the hall", example = "15") int totalColumns,
                        @Schema(description = "List of seat summaries in this hall") List<SeatDTOs.Summary> seats) {

        }

        /**
         * Summary DTO containing basic hall information.
         * Used in nested responses to avoid circular references.
         * 
         * @param id   unique identifier of the hall
         * @param name the name of the hall
         */
        @Schema(name = "HallSummary", description = "Summary DTO containing basic hall information")
        public record Summary(
                        @Schema(description = "Unique identifier of the hall", example = "1") Long id,
                        @Schema(description = "Name of the hall", example = "Main Hall") String name) {
        }
}
