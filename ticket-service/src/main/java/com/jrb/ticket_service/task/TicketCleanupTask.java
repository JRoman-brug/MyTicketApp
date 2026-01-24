package com.jrb.ticket_service.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.jrb.ticket_service.service.TicketCleanupService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TicketCleanupTask {
    private TicketCleanupService cleanupService;

    public TicketCleanupTask(TicketCleanupService cleanupService) {
        this.cleanupService = cleanupService;
    }

    @Scheduled(cron = "0 0 4 * * *")
    public void runExpiredTicketsCleanup() {
        log.info("Starting scheduled cleanup of expired tickets...");
        int deletedCount = cleanupService.cleanupPendingTickets();
        log.info("Cleanup finished. Removed {} expired tickets.", deletedCount);
    }
}
