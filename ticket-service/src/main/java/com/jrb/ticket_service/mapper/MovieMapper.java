package com.jrb.ticket_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.jrb.ticket_service.dtos.MovieDTOs;
import com.jrb.ticket_service.entity.Movie;

@Mapper(componentModel = "spring")
public interface MovieMapper {
    @Mapping(target = "id", ignore = true)
    public Movie toCreateMovie(MovieDTOs.CreateRequest request);

    public Movie toUpdateMovie(MovieDTOs.UpdateRequest request);

    public MovieDTOs.Response toResponse(Movie movie);

    public MovieDTOs.Summary toSummary(Movie movie);

}
