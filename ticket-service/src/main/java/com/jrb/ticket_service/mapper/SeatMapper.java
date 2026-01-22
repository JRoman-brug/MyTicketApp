package com.jrb.ticket_service.mapper;

import org.mapstruct.Mapper;

import com.jrb.ticket_service.dtos.SeatDTOs;
import com.jrb.ticket_service.entity.Seat;

@Mapper(componentModel = "spring")
public interface SeatMapper {
    SeatDTOs.Response toResponse(Seat seat);

    SeatDTOs.Summary toSummary(Seat seat);
}
