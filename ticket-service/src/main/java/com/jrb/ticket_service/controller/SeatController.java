package com.jrb.ticket_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jrb.ticket_service.dtos.SeatDTO;
import com.jrb.ticket_service.service.SeatService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("api/seat")
public class SeatController {

    private SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @PostMapping("/{seatId}/reserve")
    public ResponseEntity<SeatDTO> reserveSeat(@PathVariable Long seatId) {
        SeatDTO response = seatService.reserveSeat(seatId);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @PostMapping("/{seatId}/release")
    public ResponseEntity<SeatDTO> releaseSeat(@PathVariable Long seatId) {
        SeatDTO response = seatService.releaseSeat(seatId);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

}
