package com.jrb.ticket_service.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class MovieDTOs {
        private MovieDTOs() {
        }

        public record CreateRequest(
                        @NotNull String name,
                        @NotNull @Positive Integer duration,
                        @NotNull String posterUrl) {
        }

        public record UpdateRequest(
                        @NotNull Long id,
                        String name,
                        @Positive Integer duration,
                        String posterUrl) {
        }

        public record Response(
                        Long id,
                        String name,
                        Integer duration,
                        String posterUrl) {
        }

        public record Summary(Long id, String name) {
        }
}
