package com.jrb.ticket_service.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jrb.ticket_service.dtos.MovieDTOs;
import com.jrb.ticket_service.dtos.PageResponse;
import com.jrb.ticket_service.exception.base.ErrorResponse;
import com.jrb.ticket_service.service.MovieService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * REST controller for managing movies.
 * Provides endpoints for CRUD operations on movie entities.
 */
@RestController
@RequestMapping("/api/movies")
@Tag(name = "Movies", description = "Movie management endpoints")
public class MovieController {

    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @Operation(summary = "Get a movie by ID", description = "Retrieves detailed information about a specific movie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Movie found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MovieDTOs.Response.class))),
            @ApiResponse(responseCode = "404", description = "Movie not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<MovieDTOs.Response> getMovie(
            @Parameter(description = "ID of the movie to retrieve", required = true, example = "1") @PathVariable Long id) {
        MovieDTOs.Response response = movieService.getMovie(id);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @Operation(summary = "Get all movies", description = "Retrieves a paginated list of all movies")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movies retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PageResponse.class)))
    })
    @GetMapping
    public ResponseEntity<PageResponse<MovieDTOs.Response>> getAllMovies(
            @Parameter(description = "Page number (zero-based)", example = "0") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Number of items per page", example = "10") @RequestParam(defaultValue = "10") int size,
            HttpServletRequest request) {
        Page<MovieDTOs.Response> moviePage = movieService.getAllMovies(page, size);
        String baseUrl = request.getRequestURL().toString();
        PageResponse<MovieDTOs.Response> response = new PageResponse<>(moviePage, baseUrl);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Create a new movie", description = "Creates a new movie with the provided information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Movie created successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MovieDTOs.Response.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping
    public ResponseEntity<MovieDTOs.Response> createMovie(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Movie data", required = true) @Valid @RequestBody MovieDTOs.CreateRequest request) {
        MovieDTOs.Response response = movieService.createMovie(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Update a movie", description = "Updates an existing movie with the provided information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movie updated successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MovieDTOs.Response.class))),
            @ApiResponse(responseCode = "404", description = "Movie not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PatchMapping
    public ResponseEntity<MovieDTOs.Response> updateMovie(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Movie update data", required = true) @Valid @RequestBody MovieDTOs.UpdateRequest request) {
        MovieDTOs.Response response = movieService.updateMovie(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Delete a movie", description = "Deletes a movie by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Movie deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Movie not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(
            @Parameter(description = "ID of the movie to delete", required = true, example = "1") @PathVariable Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }
}
