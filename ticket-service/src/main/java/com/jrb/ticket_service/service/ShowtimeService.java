package com.jrb.ticket_service.service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jrb.ticket_service.dtos.ShowtimeDTOs;
import com.jrb.ticket_service.dtos.ScheduleDTOs.AvailableSlot;
import com.jrb.ticket_service.dtos.ScheduleDTOs.HallScheduleResponse;
import com.jrb.ticket_service.dtos.HallDTOs;
import com.jrb.ticket_service.dtos.MovieDTOs;
import com.jrb.ticket_service.dtos.SeatDTOs;
import com.jrb.ticket_service.entity.Hall;
import com.jrb.ticket_service.entity.Movie;
import com.jrb.ticket_service.entity.Seat;
import com.jrb.ticket_service.entity.Showtime;
import com.jrb.ticket_service.entity.Ticket;
import com.jrb.ticket_service.exception.domain.hall.HallNotFoundException;
import com.jrb.ticket_service.exception.domain.movie.MovieNotFoundException;
import com.jrb.ticket_service.exception.domain.showtime.ShowtimeScheduleConflictException;
import com.jrb.ticket_service.mapper.SeatMapper;
import com.jrb.ticket_service.mapper.ShowtimeMapper;
import com.jrb.ticket_service.exception.domain.showtime.ShowtimeNotFoundException;
import com.jrb.ticket_service.repository.HallRepository;
import com.jrb.ticket_service.repository.MovieRepository;
import com.jrb.ticket_service.repository.ShowTimeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ShowtimeService {
    private static final int CLEAN_TIME = 15;

    private ShowTimeRepository showTimeRepository;
    private HallRepository hallRepository;
    private MovieRepository movieRepository;
    private ShowtimeMapper showtimeMapper;
    private SeatMapper seatMapper;

    public ShowtimeService(ShowTimeRepository showTimeRepository, HallRepository hallRepository,
            MovieRepository movieRepository, ShowtimeMapper showtimeMapper, SeatMapper seatMapper) {
        this.showTimeRepository = showTimeRepository;
        this.hallRepository = hallRepository;
        this.movieRepository = movieRepository;
        this.showtimeMapper = showtimeMapper;
        this.seatMapper = seatMapper;
    }

    public ShowtimeDTOs.Response getShowtime(Long id) {
        log.debug("Fechting showtime with ID: {}", id);
        Showtime showtime = showTimeRepository.findById(id)
                .orElseThrow(() -> new ShowtimeNotFoundException(id));
        return createReposnResponseDTO(showtime.getStartTime(), showtime.getHall(), showtime.getMovie());
    }

    public Page<ShowtimeDTOs.Response> getAllShowtime(int page, int size) {
        log.debug("Fechting all showtimes");
        Pageable newPage = PageRequest.of(page, size);
        Page<Showtime> showtimePage = showTimeRepository.findAll(newPage);
        return showtimePage.map(showtimeMapper::toResponse);
    }

    public List<SeatDTOs.Response> getSeatStatusByShowtimeId(Long showtimeId) {
        Showtime showtime = findShowtimeOrThrow(showtimeId);
        List<Seat> seats = showtime.getHall().getSeats();
        List<Ticket> tickets = showtime.getTickets();
        return getSeatStatus(tickets, seats);
    }

    private Showtime findShowtimeOrThrow(Long id) {
        return showTimeRepository.findById(id).orElseThrow(() -> new ShowtimeNotFoundException(id));
    }

    private List<SeatDTOs.Response> getSeatStatus(List<Ticket> tickets, List<Seat> seats) {
        Set<Long> set = tickets.stream().map(ticket -> ticket.getSeat().getId()).collect(Collectors.toSet());
        return seats.stream().map(seat -> {
            boolean isAvailable = set.contains(seat.getId());
            return seatMapper.toResponse(seat, isAvailable);
        }).toList();
    }

    public HallScheduleResponse getHallScheduling(Long hallId, LocalDate day) {
        List<Showtime> showtimesScheduled = findAllShowtimeByHallIdAndDayOrthrow(hallId, day);
        List<AvailableSlot> slots = getAvailableSlots(showtimesScheduled, day);
        return new HallScheduleResponse(hallId, day, slots);
    }

    private List<Showtime> findAllShowtimeByHallIdAndDayOrthrow(Long hallId, LocalDate day) {
        LocalDateTime startTime = day.atStartOfDay();
        LocalDateTime endTime = startTime.plusHours(24);
        return showTimeRepository.findByHallIdAndStartTimeBetween(hallId, startTime, endTime);
    }

    private List<AvailableSlot> getAvailableSlots(List<Showtime> showtimes, LocalDate day) {
        List<AvailableSlot> availableSlots = new LinkedList<>();
        LocalDateTime startTimePointer = day.atStartOfDay();
        LocalDateTime endDay = day.atTime(LocalTime.MAX);

        if (showtimes.isEmpty()) {
            Long duration = Duration.between(startTimePointer, endDay).toMinutes();
            availableSlots.add(new AvailableSlot(startTimePointer, endDay, duration));
            return availableSlots;
        }

        Showtime showtime;
        for (int i = 0; i < showtimes.size(); i++) {
            showtime = showtimes.get(i);
            if (startTimePointer.isEqual(showtime.getStartTime())) {
                startTimePointer = getEndTimeFromShowtime(showtime);
            } else {
                AvailableSlot slot = new AvailableSlot(startTimePointer, showtime.getStartTime(),
                        getDurationBetween(startTimePointer, showtime.getStartTime()));
                availableSlots.add(slot);
                startTimePointer = getEndTimeFromShowtime(showtime);
            }
        }
        // Last check
        if (!startTimePointer.equals(endDay)) {
            AvailableSlot slot = new AvailableSlot(startTimePointer, endDay,
                    getDurationBetween(startTimePointer, endDay));
            availableSlots.add(slot);
        }
        return availableSlots;
    }

    private LocalDateTime getEndTimeFromShowtime(Showtime showtime) {
        long showtimeDuration = getShowtimeDuration(showtime);
        return showtime.getStartTime().plusMinutes(showtimeDuration);
    }

    private int getShowtimeDuration(Showtime showtime) {
        return CLEAN_TIME + showtime.getMovie().getDuration();
    }

    private Long getDurationBetween(LocalDateTime start, LocalDateTime end) {
        return Duration.between(start, end).toMinutes();
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
