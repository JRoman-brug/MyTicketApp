package com.jrb.ticket_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.jrb.ticket_service.dtos.TicketDTOs;
import com.jrb.ticket_service.entity.Ticket;

@Mapper(componentModel = "spring")
public interface TicketMapper {

    @Mapping(target = "seatLabel", source = "ticket.seat.label")
    @Mapping(target = "movieName", source = "ticket.showtime.movie.name")
    @Mapping(target = "hallName", source = "ticket.showtime.hall.name")
    @Mapping(target = "showtimeId", source = "ticket.showtime.id")
    public TicketDTOs.Response toResponse(Ticket ticket);

    public TicketDTOs.Summary toSummary(Ticket ticket);
}
