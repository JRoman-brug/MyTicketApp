package com.jrb.ticket_service.dtos;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

/**
 * Data Transfer Objects for Showtime entity operations.
 * Contains records for creating, updating, and retrieving showtime information.
 */
public class ShowtimeDTOs {
        private ShowtimeDTOs() {
        }

        /**
         * Request DTO for creating a new showtime.
         * 
         * @param startTime start time of the showtime (must be in the future)
         * @param movieId   unique identifier of the movie being shown
         * @param hallId    unique identifier of the hall where the movie will be shown
         */
        @Schema(name = "ShowtimeCreateRequest", description = "Request DTO for creating a new showtime")
        public record CreateRequest(
                        @Schema(description = "Start time of the showtime (must be in the future)", example = "2026-02-25T19:00:00", requiredMode = Schema.RequiredMode.REQUIRED) @NotNull @Future LocalDateTime startTime,
                        @Schema(description = "Unique identifier of the movie being shown", example = "1", requiredMode = Schema.RequiredMode.REQUIRED) @NotNull Long movieId,
                        @Schema(description = "Unique identifier of the hall", example = "1", requiredMode = Schema.RequiredMode.REQUIRED) @NotNull Long hallId) {
        }

        /**
         * Request DTO for updating an existing showtime.
         * All fields except id are optional, allowing partial updates.
         * 
         * @param id        unique identifier of the showtime to update
         * @param startTime new start time (optional, must be in the future if provided)
         * @param hallId    new hall identifier (optional)
         */
        @Schema(name = "ShowtimeUpdateRequest", description = "Request DTO for updating an existing showtime")
        public record UpdateRequest(
                        @Schema(description = "Unique identifier of the showtime to update", example = "1", requiredMode = Schema.RequiredMode.REQUIRED) @NotNull Long id,
                        @Schema(description = "New start time (must be in the future if provided)", example = "2026-02-25T20:00:00") @Future LocalDateTime startTime,
                        @Schema(description = "New hall identifier", example = "2") Long hallId) {
        }

        /**
         * Response DTO containing complete showtime information.
         * 
         * @param id             unique identifier of the showtime
         * @param startTime      start time of the showtime
         * @param movie          summary information of the movie being shown
         * @param hall           summary information of the hall
         * @param totalSeats     total number of seats in the hall for this showtime
         * @param availableSeats number of seats currently available for booking
         */
        @Schema(name = "ShowtimeResponse", description = "Response DTO containing complete showtime information")
        public record Response(
                        @Schema(description = "Unique identifier of the showtime", example = "1") Long id,
                        @Schema(description = "Start time of the showtime", example = "2026-02-25T19:00:00") LocalDateTime startTime,
                        @Schema(description = "Summary information of the movie being shown") MovieDTOs.Summary movie,
                        @Schema(description = "Summary information of the hall") HallDTOs.Summary hall,
                        @Schema(description = "Total number of seats in the hall", example = "150") int totalSeats,
                        @Schema(description = "Number of seats currently available for booking", example = "120") int availableSeats) {
        }

        /**
         * Summary DTO containing basic showtime information.
         * Used in lists and nested responses where complete details are not needed.
         * 
         * @param id         unique identifier of the showtime
         * @param startTime  start time of the showtime
         * @param movieTitle title of the movie being shown
         * @param hallName   name of the hall where the movie is shown
         */
        @Schema(name = "ShowtimeSummary", description = "Summary DTO containing basic showtime information")
        public record Summary(
                        @Schema(description = "Unique identifier of the showtime", example = "1") Long id,
                        @Schema(description = "Start time of the showtime", example = "2026-02-25T19:00:00") LocalDateTime startTime,
                        @Schema(description = "Title of the movie being shown", example = "Inception") String movieTitle,
                        @Schema(description = "Name of the hall", example = "Main Hall") String hallName) {
        }

}
