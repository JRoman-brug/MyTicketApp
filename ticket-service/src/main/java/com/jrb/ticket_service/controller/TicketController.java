package com.jrb.ticket_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jrb.ticket_service.dtos.TicketDTOs;
import com.jrb.ticket_service.exception.base.ErrorResponse;
import com.jrb.ticket_service.service.TicketService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * REST controller for managing tickets.
 * Provides endpoints for ticket reservation, confirmation, and cancellation.
 */
@RestController
@RequestMapping("/api/tickets")
@Tag(name = "Tickets", description = "Ticket management endpoints")
public class TicketController {
    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Operation(summary = "Reserve a ticket", description = "Reserves a seat for a specific showtime")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ticket reserved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TicketDTOs.Response.class))),
            @ApiResponse(responseCode = "400", description = "Seat already reserved or invalid request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Seat or showtime not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping
    public ResponseEntity<TicketDTOs.Response> reserveTicket(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Ticket reservation data", required = true) @Valid @RequestBody TicketDTOs.ReservationRequest request) {
        TicketDTOs.Response response = ticketService.reservateTicket(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Confirm a ticket reservation", description = "Confirms a previously reserved ticket")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ticket confirmed successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TicketDTOs.Response.class))),
            @ApiResponse(responseCode = "404", description = "Ticket not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "400", description = "Ticket already confirmed or cancelled", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/{ticketId}/confirm")
    public ResponseEntity<TicketDTOs.Response> confirmTicket(
            @Parameter(description = "ID of the ticket to confirm", required = true, example = "1") @PathVariable Long ticketId) {
        TicketDTOs.Response response = ticketService.confirmReservation(ticketId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Cancel a ticket", description = "Cancels a ticket reservation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Ticket cancelled successfully"),
            @ApiResponse(responseCode = "404", description = "Ticket not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/{ticketId}")
    public ResponseEntity<Void> deleteTicket(
            @Parameter(description = "ID of the ticket to cancel", required = true, example = "1") @PathVariable Long ticketId) {
        ticketService.cancelTicket(ticketId);
        return ResponseEntity.noContent().build();
    }
}
