package com.jrb.ticket_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jrb.ticket_service.dtos.TicketDTOs;
import com.jrb.ticket_service.service.TicketService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {
    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<TicketDTOs.Response> reserveTicket(@RequestBody TicketDTOs.ReservationRequest request) {
        TicketDTOs.Response response = ticketService.reservateTicket(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{ticketId}/confirm")
    public ResponseEntity<TicketDTOs.Response> confirmTicket(@PathVariable Long ticketId) {
        TicketDTOs.Response response = ticketService.confirmReservation(ticketId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{ticketId}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long ticketId) {
        ticketService.cancelTicket(ticketId);
        return ResponseEntity.noContent().build();
    }
}
