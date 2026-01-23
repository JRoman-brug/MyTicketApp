package com.jrb.ticket_service.service;

import org.springframework.stereotype.Service;

import com.jrb.ticket_service.dtos.MovieDTOs;
import com.jrb.ticket_service.entity.Movie;
import com.jrb.ticket_service.exception.ErrorCode;
import com.jrb.ticket_service.exception.movie.MovieNotFoundException;
import com.jrb.ticket_service.mapper.MovieMapper;
import com.jrb.ticket_service.repository.MovieRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MovieService {
    private MovieRepository movieRepository;
    private MovieMapper movieMapper;

    public MovieService(MovieRepository movieRepository, MovieMapper movieMapper) {
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
    }

    public MovieDTOs.Response getMovie(Long id) {
        log.debug("Fetching movie with ID: {}", id);
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException(ErrorCode.MOVIE_NOT_FOUND));

        return movieMapper.toResponse(movie);
    }

    public MovieDTOs.Response createMovie(MovieDTOs.CreateRequest request) {
        log.info("Attepting to create movie: {}", request.name());
        Movie newMovie = movieMapper.toCreateMovie(request);
        Movie savedMovie = movieRepository.save(newMovie);
        log.info("Movie created successfully with ID: {}", savedMovie.getId());
        return movieMapper.toResponse(savedMovie);
    }

    public MovieDTOs.Response updateMovie(MovieDTOs.UpdateRequest request) {
        log.info("Atteping to update movie with ID: {}", request.id());
        Movie updatedMovie = movieRepository.findById(request.id())
                .orElseThrow(() -> {
                    log.warn("Update failed. Movie with ID {} not found", request.id());
                    return new MovieNotFoundException(ErrorCode.MOVIE_NOT_FOUND);
                });

        if (request.name() != null)
            updatedMovie.setName(request.name());
        if (request.duration() != null)
            updatedMovie.setDuration(request.duration());
        if (request.posterUrl() != null)
            updatedMovie.setPosterUrl(request.posterUrl());
        Movie saved = movieRepository.save(updatedMovie);
        log.info("Movie with ID {} update successfully", saved.getId());
        return movieMapper.toResponse(updatedMovie);
    }

    public void deleteMovie(Long id) {
        log.info("Request to delete movie with ID: {}", id);
        movieRepository.findById(id).orElseThrow(() -> {
            log.warn("Delete failed. Movie with ID {}, not found", id);
            return new MovieNotFoundException(ErrorCode.MOVIE_NOT_FOUND);
        });
        movieRepository.deleteById(id);
        log.info("Movie with ID {} deleted successfully", id);
    }
}
