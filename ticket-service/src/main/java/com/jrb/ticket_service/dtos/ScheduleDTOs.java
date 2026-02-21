package com.jrb.ticket_service.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Data Transfer Objects for schedule and time slot operations.
 * Used to represent available time slots in halls for scheduling showtimes.
 */
public class ScheduleDTOs {
        private ScheduleDTOs() {
        }

        /**
         * Represents an available time slot in a hall.
         * 
         * @param start             start time of the available slot
         * @param end               end time of the available slot
         * @param durationInMinutes duration of the slot in minutes
         */
        @Schema(name = "AvailableSlot", description = "Represents an available time slot in a hall")
        public record AvailableSlot(
                        @Schema(description = "Start time of the available slot", example = "2026-02-20T14:00:00") LocalDateTime start,
                        @Schema(description = "End time of the available slot", example = "2026-02-20T16:30:00") LocalDateTime end,
                        @Schema(description = "Duration of the slot in minutes", example = "150") long durationInMinutes) {
        }

        /**
         * Response DTO containing all available time slots for a hall on a specific
         * date.
         * 
         * @param hallId         unique identifier of the hall
         * @param date           the date for which availability is being queried
         * @param availableSlots list of available time slots for the specified date
         */
        @Schema(name = "HallScheduleResponse", description = "Response DTO containing all available time slots for a hall")
        public record HallScheduleResponse(
                        @Schema(description = "Unique identifier of the hall", example = "1") Long hallId,
                        @Schema(description = "Date for which availability is being queried", example = "2026-02-20") LocalDate date,
                        @Schema(description = "List of available time slots for the specified date") List<AvailableSlot> availableSlots) {
        }
}
