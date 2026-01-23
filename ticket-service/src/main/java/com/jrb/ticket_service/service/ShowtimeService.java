package com.jrb.ticket_service.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jrb.ticket_service.dtos.ShowtimeDTOs;
import com.jrb.ticket_service.dtos.HallDTOs;
import com.jrb.ticket_service.dtos.MovieDTOs;
import com.jrb.ticket_service.entity.Hall;
import com.jrb.ticket_service.entity.Movie;
import com.jrb.ticket_service.entity.Showtime;
import com.jrb.ticket_service.exception.domain.hall.HallNotFoundException;
import com.jrb.ticket_service.exception.domain.movie.MovieNotFoundException;
import com.jrb.ticket_service.exception.domain.showtime.ShowtimeScheduleConflictException;
import com.jrb.ticket_service.exception.domain.showtime.ShowtimeNotFoundException;
import com.jrb.ticket_service.repository.HallRepository;
import com.jrb.ticket_service.repository.MovieRepository;
import com.jrb.ticket_service.repository.ShowTimeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ShowtimeService {
    private static final long CLEAN_TIME = 15;

    private ShowTimeRepository showTimeRepository;
    private HallRepository hallRepository;
    private MovieRepository movieRepository;

    public ShowtimeService(ShowTimeRepository showTimeRepository, HallRepository hallRepository,
            MovieRepository movieRepository) {
        this.showTimeRepository = showTimeRepository;
        this.hallRepository = hallRepository;
        this.movieRepository = movieRepository;
    }

    public ShowtimeDTOs.Response getShowtime(Long id) {
        log.debug("Fechting showtime with ID: {}", id);
        Showtime showtime = showTimeRepository.findById(id)
                .orElseThrow(() -> new ShowtimeNotFoundException(id));
        return createReposnResponseDTO(showtime.getStartTime(), showtime.getHall(), showtime.getMovie());
    }

    public ShowtimeDTOs.Response createShowtime(ShowtimeDTOs.CreateRequest request) {
        log.info("Attepting to create showtime with hallId {} and movieId: {}", request.hallId(), request.movieId());
        Hall hall = hallRepository.findById(request.hallId())
                .orElseThrow(() -> {
                    log.warn("Create failed. Hall with ID: {} not found", request.hallId());
                    return new HallNotFoundException(request.hallId());
                });
        Movie movie = movieRepository.findById(request.movieId())
                .orElseThrow(() -> {
                    log.warn("Create failed. Movie with ID: {} not found", request.movieId());
                    return new MovieNotFoundException(request.movieId());
                });

        validateCollision(hall.getId(), request.startTime(), movie.getDuration());
        Showtime newShowtime = Showtime.builder()
                .startTime(request.startTime())
                .movie(movie)
                .hall(hall)
                .build();
        Showtime savedShowtime = showTimeRepository.save(newShowtime);
        return createReposnResponseDTO(savedShowtime.getStartTime(), savedShowtime.getHall(), savedShowtime.getMovie());
    }

    private void validateCollision(Long hallId, LocalDateTime newStart, int duration) {
        List<Showtime> showtimes = showTimeRepository.findByHallId(hallId);
        LocalDateTime newEnd = newStart.plusMinutes(duration + CLEAN_TIME);
        log.debug("Validate colition from hallId {} with startTime {} and endTime", hallId, newStart, newEnd);

        boolean hasColisition = showtimes.stream().anyMatch(showtime -> {
            LocalDateTime exStart = showtime.getStartTime();
            long exDuration = showtime.getMovie().getDuration() + CLEAN_TIME;
            LocalDateTime exEnd = exStart.plusMinutes(exDuration);

            return newStart.isBefore(exEnd) && newEnd.isAfter(exStart);
        });
        if (hasColisition) {
            log.warn("Create failed. Has a colition with hallID {} start time {} and end time {}", hallId, newStart,
                    newEnd);
            throw new ShowtimeScheduleConflictException();
        }
    }

    private ShowtimeDTOs.Response createReposnResponseDTO(LocalDateTime startTime, Hall hall, Movie movie) {
        int totalSeats = hall.getSeats().size();
        int availableSeats = totalSeats;
        HallDTOs.Summary hallDTO = new HallDTOs.Summary(hall.getId(), hall.getName());
        MovieDTOs.Summary movieDTO = new MovieDTOs.Summary(movie.getId(), movie.getName());
        return new ShowtimeDTOs.Response(startTime, movieDTO, hallDTO, totalSeats, availableSeats);
    }

    public ShowtimeDTOs.Response updateShowtime(ShowtimeDTOs.UpdateRequest request) {
        log.info("Attepng to update showtime with ID: {}", request.id());
        Showtime showtimeToUpdate = showTimeRepository.findById(request.id())
                .orElseThrow(() -> {
                    log.warn("Update failed. Showtime with ID {} not found", request.id());
                    return new ShowtimeNotFoundException(request.id());
                });

        if (request.hallId() != null) {
            Hall hall = hallRepository.findById(request.hallId())
                    .orElseThrow(() -> {
                        log.warn("Update failed. Movie with ID {} found: ", request.hallId());
                        return new HallNotFoundException(request.hallId());
                    });
            showtimeToUpdate.setHall(hall);
        }
        if (request.startTime() != null)
            showtimeToUpdate.setStartTime(request.startTime());

        Showtime showtimeUpdated = showTimeRepository.save(showtimeToUpdate);
        log.info("Showtime with ID {} update successfully", showtimeToUpdate.getId());
        return createReposnResponseDTO(showtimeUpdated.getStartTime(), showtimeUpdated.getHall(),
                showtimeUpdated.getMovie());
    }

    public void deleteShowtime(Long id) {
        log.info("Request to delete showtime with ID {}", id);
        showTimeRepository.findById(id).orElseThrow(() -> {
            log.warn("Delete failed. Showtime with ID {} not found", id);
            return new ShowtimeNotFoundException(id);
        });
        showTimeRepository.deleteById(id);
        log.info("Showtime with ID {} delete successfully.", id);
    }
}
