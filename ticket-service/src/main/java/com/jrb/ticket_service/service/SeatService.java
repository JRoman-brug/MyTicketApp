package com.jrb.ticket_service.service;

import org.springframework.stereotype.Service;

import com.jrb.ticket_service.dtos.SeatDTO;
import com.jrb.ticket_service.entity.Seat;
import com.jrb.ticket_service.entity.enums.TicketStatus;
import com.jrb.ticket_service.exception.ErrorCode;
import com.jrb.ticket_service.exception.SeatIsReservedException;
import com.jrb.ticket_service.exception.SeatNotFound;
import com.jrb.ticket_service.repository.SeatRepository;

@Service
public class SeatService {

    private SeatRepository seatRepository;

    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public SeatDTO reserveSeat(Long id) {
        Seat seat = seatRepository.findById(id).orElseThrow(() -> new SeatNotFound(ErrorCode.SEAT_NOT_FOUND));
        if (seat.isReserved())
            throw new SeatIsReservedException(ErrorCode.SEAT_RESERVED);
        seat.setStatus(TicketStatus.BLOQUEADO);
        seatRepository.save(seat);
        return new SeatDTO(seat.getRow(), seat.getColumn(), seat.getLabel(), seat.getStatus());
    }

    public SeatDTO releaseSeat(Long id) {
        Seat seat = seatRepository.findById(id).orElseThrow(() -> new SeatNotFound(ErrorCode.SEAT_NOT_FOUND));
        seat.setStatus(TicketStatus.LIBRE);
        seatRepository.save(seat);
        return new SeatDTO(seat.getRow(), seat.getColumn(), seat.getLabel(), seat.getStatus());
    }
}
