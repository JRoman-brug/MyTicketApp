package com.jrb.ticket_service.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.jrb.ticket_service.dtos.PageResponse;
import com.jrb.ticket_service.dtos.ShowtimeDTOs;
import com.jrb.ticket_service.service.ShowtimeService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/showtimes")
public class ShowtimeController {
    private ShowtimeService showtimeService;

    public ShowtimeController(ShowtimeService showtimeService) {
        this.showtimeService = showtimeService;
    }

    @GetMapping("/{showtimeId}")
    public ResponseEntity<ShowtimeDTOs.Response> getShowtime(@PathVariable Long showtimeId) {
        ShowtimeDTOs.Response response = showtimeService.getShowtime(showtimeId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<PageResponse<ShowtimeDTOs.Response>> getAllShowtime(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            HttpServletRequest request) {
        Page<ShowtimeDTOs.Response> allShowtimes = showtimeService.getAllShowtime(page, size);
        String baseUrl = request.getRequestURL().toString();
        PageResponse<ShowtimeDTOs.Response> response = new PageResponse<>(allShowtimes, baseUrl);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ShowtimeDTOs.Response> createShowtime(@RequestBody ShowtimeDTOs.CreateRequest request) {
        ShowtimeDTOs.Response response = showtimeService.createShowtime(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity<ShowtimeDTOs.Response> updateShowtime(@RequestBody ShowtimeDTOs.UpdateRequest request) {
        ShowtimeDTOs.Response response = showtimeService.updateShowtime(request);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{showtimeId}")
    public ResponseEntity<Void> deleteShowtime(@PathVariable Long showtimeId) {
        showtimeService.deleteShowtime(showtimeId);
        return ResponseEntity.noContent().build();
    }
}
