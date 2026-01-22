package com.jrb.ticket_service.service;

import org.springframework.stereotype.Service;

import com.jrb.ticket_service.dtos.SeatDTOs;
import com.jrb.ticket_service.entity.Seat;
import com.jrb.ticket_service.exception.ErrorCode;
import com.jrb.ticket_service.exception.SeatNotFound;
import com.jrb.ticket_service.repository.SeatRepository;

@Service
public class SeatService {

    private SeatRepository seatRepository;

    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public SeatDTOs.Response reserveSeat(Long id) {
        Seat seat = seatRepository.findById(id).orElseThrow(() -> new SeatNotFound(ErrorCode.SEAT_NOT_FOUND));
        seatRepository.save(seat);
        return new SeatDTOs.Response(seat.getId(), seat.getRow(), seat.getColumn(), seat.getLabel());
    }

    public SeatDTOs.Response releaseSeat(Long id) {
        Seat seat = seatRepository.findById(id).orElseThrow(() -> new SeatNotFound(ErrorCode.SEAT_NOT_FOUND));
        seatRepository.save(seat);
        return new SeatDTOs.Response(seat.getId(), seat.getRow(), seat.getColumn(), seat.getLabel());
    }
}
