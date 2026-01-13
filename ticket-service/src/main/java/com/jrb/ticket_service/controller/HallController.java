package com.jrb.ticket_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jrb.ticket_service.dtos.CreateHallDTO;
import com.jrb.ticket_service.service.HallService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/hall")
public class HallController {
    private HallService hallService;

    public HallController(HallService hallService) {
        this.hallService = hallService;
    }

    @GetMapping("/create")
    public ResponseEntity<String> createHall(@Valid @RequestBody CreateHallDTO request) {
        hallService.createHall(request);
        return new ResponseEntity<>("Hall was created successfully", HttpStatus.CREATED);
    }
}
