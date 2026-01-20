package com.jrb.ticket_service.dtos;

// Todo refactor all hall dtos
public class HallDTOs {
    private HallDTOs() {
    }

    public record Summary(
            Long id,
            String name) {
    }
}
