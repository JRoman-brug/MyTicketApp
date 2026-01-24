package com.jrb.ticket_service.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jrb.ticket_service.entity.Ticket;
import com.jrb.ticket_service.entity.enums.TicketStatus;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    public boolean existsBySeatIdAndShowtimeId(Long seatId, Long showtimeId);

    public int deleteByStatusAndShowtimeStartTimeBefore(TicketStatus status, LocalDateTime limit);
}
