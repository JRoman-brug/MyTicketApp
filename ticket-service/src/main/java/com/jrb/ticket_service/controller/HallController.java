package com.jrb.ticket_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jrb.ticket_service.dtos.CreateHallDTO;
import com.jrb.ticket_service.dtos.DeleteHallRequestDTO;
import com.jrb.ticket_service.dtos.GetHallResponseDTO;
import com.jrb.ticket_service.service.HallService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/hall")
public class HallController {
    private HallService hallService;

    public HallController(HallService hallService) {
        this.hallService = hallService;
    }

    @GetMapping("/{hallId}")
    public ResponseEntity<GetHallResponseDTO> getHall(@PathVariable Long hallId) {
        GetHallResponseDTO response = hallService.getHall(hallId);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @PostMapping("/")
    public ResponseEntity<String> createHall(@Valid @RequestBody CreateHallDTO request) {
        hallService.createHall(request);
        return new ResponseEntity<>("Hall was created successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/")
    public ResponseEntity<String> deleteHall(@RequestBody DeleteHallRequestDTO request) {
        hallService.deleteHall(request);
        return new ResponseEntity<>("Hall was deleted successfully", HttpStatus.ACCEPTED);
    }
}
