package com.jrb.ticket_service.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ScheduleDTOs {
    private ScheduleDTOs() {
    }

    public record AvailableSlot(
            LocalDateTime start,
            LocalDateTime end,
            long durationInMinutes) {
    }

    public record HallScheduleResponse(
            Long hallId,
            LocalDate date,
            List<AvailableSlot> availableSlots) {
    }
}
