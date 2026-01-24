package com.jrb.ticket_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jrb.ticket_service.entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    public boolean existsBySeatIdAndShowtimeId(Long seatId, Long showtimeId);
}
