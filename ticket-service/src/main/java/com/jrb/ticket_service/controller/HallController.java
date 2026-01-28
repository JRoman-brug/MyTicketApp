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
import com.jrb.ticket_service.service.HallService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@RestController
@RequestMapping("api/hall")
public class HallController {
    private HallService hallService;

    public HallController(HallService hallService) {
        this.hallService = hallService;
    }

    @GetMapping("/{hallId}")
    public ResponseEntity<HallDTOs.Response> getHall(@PathVariable Long hallId) {
        log.debug("Rest request to get a Hall: {}", hallId);
        HallDTOs.Response response = hallService.getHall(hallId);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<List<HallDTOs.Response>> getMethodName() {
        List<HallDTOs.Response> response = hallService.getAllHall();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HallDTOs.Response> createHall(@RequestBody HallDTOs.CreateRequest request) {
        log.info("Rest request to create a Hall: {}", request.name());
        HallDTOs.Response response = hallService.createHall(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{hallId}")
    public ResponseEntity<Void> deleteHall(@PathVariable Long hallId) {
        log.info("Rest request to delete a Hall: {}", hallId);
        hallService.deleteHall(hallId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
