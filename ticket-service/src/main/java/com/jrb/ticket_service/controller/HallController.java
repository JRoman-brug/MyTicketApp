package com.jrb.ticket_service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jrb.ticket_service.dtos.HallDTOs;
import com.jrb.ticket_service.exception.base.ErrorResponse;
import com.jrb.ticket_service.service.HallService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

/**
 * REST controller for managing halls.
 * Provides endpoints for CRUD operations on hall entities.
 */
@Slf4j
@RestController
@RequestMapping("api/hall")
@Tag(name = "Halls", description = "Hall management endpoints")
public class HallController {
    private HallService hallService;

    public HallController(HallService hallService) {
        this.hallService = hallService;
    }

    @Operation(summary = "Get a hall by ID", description = "Retrieves detailed information about a specific hall including its seats")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Hall found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = HallDTOs.Response.class))),
            @ApiResponse(responseCode = "404", description = "Hall not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{hallId}")
    public ResponseEntity<HallDTOs.Response> getHall(
            @Parameter(description = "ID of the hall to retrieve", required = true, example = "1") @PathVariable Long hallId) {
        log.debug("Rest request to get a Hall: {}", hallId);
        HallDTOs.Response response = hallService.getHall(hallId);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @Operation(summary = "Get all halls", description = "Retrieves a list of all halls")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Halls retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = HallDTOs.Response.class)))
    })
    @GetMapping
    public ResponseEntity<List<HallDTOs.Response>> getAllHalls() {
        List<HallDTOs.Response> response = hallService.getAllHall();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Create a new hall", description = "Creates a new hall with the specified seating layout")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Hall created successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = HallDTOs.Response.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping
    public ResponseEntity<HallDTOs.Response> createHall(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Hall data including seating layout", required = true) @Valid @RequestBody HallDTOs.CreateRequest request) {
        log.info("Rest request to create a Hall: {}", request.name());
        HallDTOs.Response response = hallService.createHall(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Delete a hall", description = "Deletes a hall by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Hall deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Hall not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("/{hallId}")
    public ResponseEntity<Void> deleteHall(
            @Parameter(description = "ID of the hall to delete", required = true, example = "1") @PathVariable Long hallId) {
        log.info("Rest request to delete a Hall: {}", hallId);
        hallService.deleteHall(hallId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
