package com.jrb.ticket_service.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Data Transfer Objects for Seat entity operations.
 * Contains records for retrieving seat information and seat summaries.
 */
public class SeatDTOs {

    /**
     * Response DTO containing complete seat information.
     * 
     * @param id          unique identifier of the seat
     * @param row         row number where the seat is located
     * @param column      column number where the seat is located
     * @param label       human-readable label for the seat (e.g., "A1", "B5")
     * @param isAvailable indicates whether the seat is currently available for
     *                    booking
     */
    @Schema(name = "SeatResponse", description = "Response DTO containing complete seat information")
    public record Response(
            @Schema(description = "Unique identifier of the seat", example = "1") Long id,
            @Schema(description = "Row number where the seat is located", example = "5") int row,
            @Schema(description = "Column number where the seat is located", example = "10") int column,
            @Schema(description = "Human-readable label for the seat", example = "A1") String label,
            @Schema(description = "Whether the seat is currently available for booking", example = "true") boolean isAvailable) {
    }

    /**
     * Summary DTO containing basic seat information.
     * Used in nested responses where full seat details are not required.
     * 
     * @param id    unique identifier of the seat
     * @param label human-readable label for the seat
     */
    @Schema(name = "SeatSummary", description = "Summary DTO containing basic seat information")
    public record Summary(
            @Schema(description = "Unique identifier of the seat", example = "1") Long id,
            @Schema(description = "Human-readable label for the seat", example = "A1") String label) {
    }
}
