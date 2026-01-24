package com.jrb.ticket_service.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.jrb.ticket_service.entity.enums.TicketStatus;
import com.jrb.ticket_service.repository.TicketRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TicketCleanupService {
    private TicketRepository ticketRepository;

    public TicketCleanupService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public int cleanupPendingTickets() {
        LocalDateTime localtime = LocalDateTime.now();
        return ticketRepository.deleteByStatusAndShowtimeStartTimeBefore(TicketStatus.CANCELLED, localtime);
    }
}
