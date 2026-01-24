package com.jrb.ticket_service.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jrb.ticket_service.dtos.TicketDTOs;
import com.jrb.ticket_service.entity.Hall;
import com.jrb.ticket_service.entity.Seat;
import com.jrb.ticket_service.entity.Showtime;
import com.jrb.ticket_service.entity.Ticket;
import com.jrb.ticket_service.entity.enums.TicketStatus;
import com.jrb.ticket_service.exception.domain.seat.SeatNotFoundException;
import com.jrb.ticket_service.exception.domain.showtime.ShowtimeNotFoundException;
import com.jrb.ticket_service.exception.domain.ticket.SeatAlreadyOcuppiedException;
import com.jrb.ticket_service.exception.domain.ticket.SeatNotBelongingToHallException;
import com.jrb.ticket_service.exception.domain.ticket.TicketNotFound;
import com.jrb.ticket_service.mapper.TicketMapper;
import com.jrb.ticket_service.repository.SeatRepository;
import com.jrb.ticket_service.repository.ShowTimeRepository;
import com.jrb.ticket_service.repository.TicketRepository;

@Service
public class TicketService {
    private TicketRepository ticketRepository;
    private ShowTimeRepository showTimeRepository;
    private SeatRepository seatRepository;
    private TicketMapper ticketMapper;

    public TicketService(TicketRepository ticketRepository, ShowTimeRepository showTimeRepository,
            SeatRepository seatRepository, TicketMapper ticketMapper) {
        this.ticketRepository = ticketRepository;
        this.showTimeRepository = showTimeRepository;
        this.seatRepository = seatRepository;
        this.ticketMapper = ticketMapper;
    }

    @Transactional
    public TicketDTOs.Response reservateTicket(TicketDTOs.ReservationRequest request) {
        Showtime showtime = findShowtimeOrThrow(request.showtimeId());
        Seat seat = findSeatOrThrow(request.seatId());
        validateSeatBelongsToHall(showtime.getHall(), seat.getId());
        validatedSeatAvailable(seat.getId(), showtime.getId());

        Ticket newTicket = Ticket.builder()
                .seat(seat)
                .status(TicketStatus.RESERVE)
                .showtime(showtime)
                .build();
        Ticket savedTicket = ticketRepository.save(newTicket);
        return ticketMapper.toResponse(savedTicket);
    }

    private Showtime findShowtimeOrThrow(Long showtimeId) {
        return showTimeRepository.findById(showtimeId)
                .orElseThrow(() -> new ShowtimeNotFoundException(showtimeId));
    }

    private Seat findSeatOrThrow(Long seatId) {
        return seatRepository.findById(seatId).orElseThrow(() -> new SeatNotFoundException(seatId));
    }

    private void validateSeatBelongsToHall(Hall hall, Long seatId) {
        boolean exist = hall.getSeats().stream().anyMatch(seat -> seat.getId().equals(seatId));
        if (!exist)
            throw new SeatNotBelongingToHallException(hall.getId(), seatId);
    }

    private void validatedSeatAvailable(Long seatId, Long showtimeId) {
        boolean isAlreadyOcuppied = ticketRepository.existsBySeatIdAndShowtimeId(seatId, showtimeId);
        if (isAlreadyOcuppied)
            throw new SeatAlreadyOcuppiedException(seatId, showtimeId);
    }

    public TicketDTOs.Response confirmReservation(Long ticketId) {
        Ticket ticket = findTicketOrThrow(ticketId);
        ticket.setStatus(TicketStatus.CONFIRMED);
        Ticket savedTicket = ticketRepository.save(ticket);
        return ticketMapper.toResponse(savedTicket);
    }

    private Ticket findTicketOrThrow(Long tickeId) {
        return ticketRepository.findById(tickeId).orElseThrow(() -> new TicketNotFound(tickeId));
    }
}
