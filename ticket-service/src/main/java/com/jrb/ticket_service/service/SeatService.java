package com.jrb.ticket_service.service;

import org.springframework.stereotype.Service;

import com.jrb.ticket_service.dtos.SeatDTOs;
import com.jrb.ticket_service.entity.Seat;
import com.jrb.ticket_service.exception.SeatNotFound;
import com.jrb.ticket_service.exception.base.ErrorCode;
import com.jrb.ticket_service.mapper.SeatMapper;
import com.jrb.ticket_service.repository.SeatRepository;

@Service
public class SeatService {

    private SeatRepository seatRepository;
    private SeatMapper seatMapper;

    public SeatService(SeatRepository seatRepository, SeatMapper seatMapper) {
        this.seatRepository = seatRepository;
        this.seatMapper = seatMapper;
    }

    public SeatDTOs.Response reserveSeat(Long id) {
        Seat seat = seatRepository.findById(id).orElseThrow(() -> new SeatNotFound(ErrorCode.SEAT_NOT_FOUND));
        seatRepository.save(seat);
        return seatMapper.toResponse(seat);
    }

    public SeatDTOs.Response releaseSeat(Long id) {
        Seat seat = seatRepository.findById(id).orElseThrow(() -> new SeatNotFound(ErrorCode.SEAT_NOT_FOUND));
        seatRepository.save(seat);
        return seatMapper.toResponse(seat);
    }
}
