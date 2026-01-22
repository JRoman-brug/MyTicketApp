package com.jrb.ticket_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.jrb.ticket_service.dtos.ShowtimeDTOs;
import com.jrb.ticket_service.entity.Showtime;

@Mapper(componentModel = "spring")
public interface ShowtimeMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tickets", ignore = true)
    @Mapping(target = "hall", ignore = true)
    @Mapping(target = "movie", ignore = true)
    public Showtime toCreateShowtime(ShowtimeDTOs.CreateRequest request);

    @Mapping(target = "tickets", ignore = true)
    @Mapping(target = "hall", ignore = true)
    @Mapping(target = "movie", ignore = true)
    public Showtime toCreateShowtime(ShowtimeDTOs.UpdateRequest request);

    @Mapping(target = "totalSeats", expression = "java(showtime.getHall().getSeats().size())")
    @Mapping(target = "availableSeats", expression = "java(showtime.getHall().getSeats().size() - showtime.getTickets().size())")
    public ShowtimeDTOs.Response toResponse(Showtime showtime);

    @Mapping(target = "movieTitle", source = "movie.name")
    @Mapping(target = "hallName", source = "hall.name")
    public ShowtimeDTOs.Summary toSummary(Showtime showtime);
}
