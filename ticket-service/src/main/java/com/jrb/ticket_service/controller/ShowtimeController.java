package com.jrb.ticket_service.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.jrb.ticket_service.dtos.PageResponse;
import com.jrb.ticket_service.dtos.ScheduleDTOs;
import com.jrb.ticket_service.dtos.SeatDTOs;
import com.jrb.ticket_service.dtos.ShowtimeDTOs;
import com.jrb.ticket_service.exception.base.ErrorResponse;
import com.jrb.ticket_service.service.ShowtimeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * REST controller for managing showtimes.
 * Provides endpoints for scheduling movie showtimes in halls.
 */
@RestController
@RequestMapping("/api/showtimes")
@Tag(name = "Showtimes", description = "Showtime management endpoints")
public class ShowtimeController {
    private ShowtimeService showtimeService;

    public ShowtimeController(ShowtimeService showtimeService) {
        this.showtimeService = showtimeService;
    }

    @Operation(summary = "Get a showtime by ID", description = "Retrieves detailed information about a specific showtime")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Showtime found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ShowtimeDTOs.Response.class))),
            @ApiResponse(responseCode = "404", description = "Showtime not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{showtimeId}")
    public ResponseEntity<ShowtimeDTOs.Response> getShowtime(
            @Parameter(description = "ID of the showtime to retrieve", required = true, example = "1") @PathVariable Long showtimeId) {
        ShowtimeDTOs.Response response = showtimeService.getShowtime(showtimeId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Get all showtimes", description = "Retrieves a paginated list of all showtimes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Showtimes retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PageResponse.class)))
    })
    @GetMapping
    public ResponseEntity<PageResponse<ShowtimeDTOs.Response>> getAllShowtime(
            @Parameter(description = "Page number (zero-based)", example = "0") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Number of items per page", example = "10") @RequestParam(defaultValue = "10") int size,
            HttpServletRequest request) {
        Page<ShowtimeDTOs.Response> allShowtimes = showtimeService.getAllShowtime(page, size);
        String baseUrl = request.getRequestURL().toString();
        PageResponse<ShowtimeDTOs.Response> response = new PageResponse<>(allShowtimes, baseUrl);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Get seat status for a showtime", description = "Retrieves the availability status of all seats for a specific showtime")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Seat status retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SeatDTOs.Response.class))),
            @ApiResponse(responseCode = "404", description = "Showtime not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("{showtimeId}/seatStatus")
    public ResponseEntity<List<SeatDTOs.Response>> getSeatStatusByShowtimeId(
            @Parameter(description = "ID of the showtime", required = true, example = "1") @PathVariable Long showtimeId) {
        List<SeatDTOs.Response> response = showtimeService.getSeatStatusByShowtimeId(showtimeId);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get hall schedule", description = "Retrieves available time slots for a hall on a specific date")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hall schedule retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ScheduleDTOs.HallScheduleResponse.class))),
            @ApiResponse(responseCode = "404", description = "Hall not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/hallSchedule")
    public ResponseEntity<ScheduleDTOs.HallScheduleResponse> getHallSchedule(
            @Parameter(description = "ID of the hall", required = true, example = "1") @RequestParam(required = true) Long hallId,
            @Parameter(description = "Date for which to check availability", required = true, example = "2026-02-25") @RequestParam(required = true) LocalDate day) {
        ScheduleDTOs.HallScheduleResponse response = showtimeService.getHallScheduling(hallId, day);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Create a new showtime", description = "Creates a new showtime for a movie in a specific hall")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Showtime created successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ShowtimeDTOs.Response.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data or showtime conflict", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Movie or hall not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping
    public ResponseEntity<ShowtimeDTOs.Response> createShowtime(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Showtime data", required = true) @Valid @RequestBody ShowtimeDTOs.CreateRequest request) {
        ShowtimeDTOs.Response response = showtimeService.createShowtime(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Update a showtime", description = "Updates an existing showtime")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Showtime updated successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ShowtimeDTOs.Response.class))),
            @ApiResponse(responseCode = "404", description = "Showtime not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data or showtime conflict", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PatchMapping
    public ResponseEntity<ShowtimeDTOs.Response> updateShowtime(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Showtime update data", required = true) @Valid @RequestBody ShowtimeDTOs.UpdateRequest request) {
        ShowtimeDTOs.Response response = showtimeService.updateShowtime(request);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @Operation(summary = "Delete a showtime", description = "Deletes a showtime by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Showtime deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Showtime not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("/{showtimeId}")
    public ResponseEntity<Void> deleteShowtime(
            @Parameter(description = "ID of the showtime to delete", required = true, example = "1") @PathVariable Long showtimeId) {
        showtimeService.deleteShowtime(showtimeId);
        return ResponseEntity.noContent().build();
    }
}
