package com.jrb.ticket_service.dtos;

import com.jrb.ticket_service.entity.enums.TicketStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

/**
 * Data Transfer Objects for Ticket entity operations.
 * Contains records for reserving tickets and retrieving ticket information.
 */
public class TicketDTOs {
        private TicketDTOs() {
        }

        /**
         * Request DTO for creating a new ticket reservation.
         * 
         * @param seatId     unique identifier of the seat to reserve
         * @param showtimeId unique identifier of the showtime for the reservation
         */
        @Schema(name = "TicketReservationRequest", description = "Request DTO for creating a new ticket reservation")
        public record ReservationRequest(
                        @Schema(description = "Unique identifier of the seat to reserve", example = "1", requiredMode = Schema.RequiredMode.REQUIRED) @NotNull Long seatId,
                        @Schema(description = "Unique identifier of the showtime", example = "1", requiredMode = Schema.RequiredMode.REQUIRED) @NotNull Long showtimeId) {
        }

        /**
         * Response DTO containing complete ticket information.
         * 
         * @param id         unique identifier of the ticket
         * @param seatLabel  human-readable label of the reserved seat
         * @param movieName  title of the movie for this ticket
         * @param hallName   name of the hall where the movie will be shown
         * @param showtimeId unique identifier of the showtime
         * @param status     current status of the ticket (e.g., RESERVED, CONFIRMED,
         *                   CANCELLED)
         * @param userId     unique identifier of the user who owns the ticket
         */
        @Schema(name = "TicketResponse", description = "Response DTO containing complete ticket information")
        public record Response(
                        @Schema(description = "Unique identifier of the ticket", example = "1") Long id,
                        @Schema(description = "Human-readable label of the reserved seat", example = "A1") String seatLabel,
                        @Schema(description = "Title of the movie", example = "Inception") String movieName,
                        @Schema(description = "Name of the hall", example = "Main Hall") String hallName,
                        @Schema(description = "Unique identifier of the showtime", example = "1") Long showtimeId,
                        @Schema(description = "Current status of the ticket", example = "RESERVED") TicketStatus status,
                        @Schema(description = "Unique identifier of the user who owns the ticket", example = "123") Long userId) {
        }

        /**
         * Summary DTO containing basic ticket information.
         * Used in lists and nested responses where full ticket details are not
         * required.
         * 
         * @param id     unique identifier of the ticket
         * @param status current status of the ticket
         * @param userId unique identifier of the user who owns the ticket
         */
        @Schema(name = "TicketSummary", description = "Summary DTO containing basic ticket information")
        public record Summary(
                        @Schema(description = "Unique identifier of the ticket", example = "1") Long id,
                        @Schema(description = "Current status of the ticket", example = "RESERVED") TicketStatus status,
                        @Schema(description = "Unique identifier of the user who owns the ticket", example = "123") Long userId) {

        }

}
