package com.jrb.ticket_service.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * Data Transfer Objects for Movie entity operations.
 * Contains records for creating, updating, and retrieving movie information.
 */
public class MovieDTOs {
        private MovieDTOs() {
        }

        /**
         * Request DTO for creating a new movie.
         * 
         * @param name      the title of the movie
         * @param duration  the duration of the movie in minutes (must be positive)
         * @param posterUrl URL to the movie poster image
         */
        @Schema(name = "MovieCreateRequest", description = "Request DTO for creating a new movie")
        public record CreateRequest(
                        @Schema(description = "Title of the movie", example = "Inception", requiredMode = Schema.RequiredMode.REQUIRED) @NotNull String name,
                        @Schema(description = "Duration of the movie in minutes", example = "148", minimum = "1", requiredMode = Schema.RequiredMode.REQUIRED) @NotNull @Positive Integer duration,
                        @Schema(description = "URL to the movie poster image", example = "https://example.com/posters/inception.jpg", requiredMode = Schema.RequiredMode.REQUIRED) @NotNull String posterUrl) {
        }

        /**
         * Request DTO for updating an existing movie.
         * All fields except id are optional, allowing partial updates.
         * 
         * @param id        unique identifier of the movie to update
         * @param name      new title for the movie (optional)
         * @param duration  new duration in minutes (optional, must be positive if
         *                  provided)
         * @param posterUrl new poster URL (optional)
         */
        @Schema(name = "MovieUpdateRequest", description = "Request DTO for updating an existing movie")
        public record UpdateRequest(
                        @Schema(description = "Unique identifier of the movie to update", example = "1", requiredMode = Schema.RequiredMode.REQUIRED) @NotNull Long id,
                        @Schema(description = "New title for the movie", example = "Inception: Director's Cut") String name,
                        @Schema(description = "New duration in minutes", example = "152", minimum = "1") @Positive Integer duration,
                        @Schema(description = "New poster URL", example = "https://example.com/posters/inception-dc.jpg") String posterUrl) {
        }

        /**
         * Response DTO containing complete movie information.
         * 
         * @param id        unique identifier of the movie
         * @param name      the title of the movie
         * @param duration  the duration of the movie in minutes
         * @param posterUrl URL to the movie poster image
         */
        @Schema(name = "MovieResponse", description = "Response DTO containing complete movie information")
        public record Response(
                        @Schema(description = "Unique identifier of the movie", example = "1") Long id,
                        @Schema(description = "Title of the movie", example = "Inception") String name,
                        @Schema(description = "Duration of the movie in minutes", example = "148") Integer duration,
                        @Schema(description = "URL to the movie poster image", example = "https://example.com/posters/inception.jpg") String posterUrl) {
        }

        /**
         * Summary DTO containing basic movie information.
         * Used in nested responses to avoid excessive data transfer.
         * 
         * @param id   unique identifier of the movie
         * @param name the title of the movie
         */
        @Schema(name = "MovieSummary", description = "Summary DTO containing basic movie information")
        public record Summary(
                        @Schema(description = "Unique identifier of the movie", example = "1") Long id,
                        @Schema(description = "Title of the movie", example = "Inception") String name) {
        }
}
