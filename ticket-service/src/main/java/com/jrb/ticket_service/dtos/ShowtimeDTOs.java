package com.jrb.ticket_service.dtos;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

public class ShowtimeDTOs {
        private ShowtimeDTOs() {
        }

        public record CreateRequest(
                        @NotNull @Future LocalDateTime startTime,
                        @NotNull Long movieId,
                        @NotNull Long hallId) {
        }

        public record UpdateRequest(
                        @NotNull Long id,
                        @Future LocalDateTime startTime,
                        Long hallId) {
        }

        public record Response(
                        Long id,
                        LocalDateTime startTime,
                        MovieDTOs.Summary movie,
                        HallDTOs.Summary hall,
                        int totalSeats,
                        int availableSeats) {
        }

        public record Summary(
                        Long id,
                        LocalDateTime startTime,
                        String movieTitle,
                        String hallName) {
        }

}
