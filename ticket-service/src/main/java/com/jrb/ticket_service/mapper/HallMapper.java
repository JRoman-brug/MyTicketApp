package com.jrb.ticket_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.jrb.ticket_service.dtos.HallDTOs;
import com.jrb.ticket_service.entity.Hall;

@Mapper(componentModel = "spring")
public interface HallMapper {

    public HallDTOs.Response toResponse(Hall hall);

    public HallDTOs.Summary toSummary(Hall hall);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "seats", ignore = true)
    public Hall toEntity(HallDTOs.CreateRequest request);

}
