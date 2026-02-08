package com.jrb.ticket_service.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.jrb.ticket_service.dtos.ScheduleDTOs.AvailableSlot;
import com.jrb.ticket_service.dtos.ScheduleDTOs.HallScheduleResponse;
import com.jrb.ticket_service.entity.Movie;
import com.jrb.ticket_service.entity.Showtime;
import com.jrb.ticket_service.mapper.SeatMapper;
import com.jrb.ticket_service.mapper.ShowtimeMapper;
import com.jrb.ticket_service.repository.HallRepository;
import com.jrb.ticket_service.repository.MovieRepository;
import com.jrb.ticket_service.repository.ShowTimeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(MockitoExtension.class)
class ShowtimeServiceTest {

    @Mock
    private ShowTimeRepository showTimeRepository;
    @Mock
    private HallRepository hallRepository;
    @Mock
    private MovieRepository movieRepository;
    @Mock
    private ShowtimeMapper showtimeMapper;
    @Mock
    private SeatMapper seatMapper;
    // Object test
    private ShowtimeService showtimeService;

    @BeforeEach
    void setUp() {
        // Inicializa los mocks anotados con @Mock
        MockitoAnnotations.openMocks(this);
        // 2. Inyectamos el mock manualmente por el constructor
        showtimeService = new ShowtimeService(showTimeRepository, hallRepository, movieRepository, showtimeMapper,
                seatMapper);
    }

    @Test
    void whenNoShowsExist_shouldReturnFullDayAsAvailable() {
        // --- ARRANGE (Preparar) ---
        Long hallId = 1L;
        LocalDate date = LocalDate.of(2026, 5, 25);

        when(showTimeRepository.findByHallIdAndStartTimeBetween(eq(hallId), any(LocalDateTime.class),
                any(LocalDateTime.class)))
                .thenReturn(Collections.emptyList());

        // --- ACT (Actuar / Ejecutar) ---
        HallScheduleResponse response = showtimeService.getHallScheduling(hallId, date);
        List<AvailableSlot> result = response.availableSlots();
        // --- ASSERT (Verificar) ---
        log.debug(result.toString());
        assertNotNull(result);
        assertEquals(1, result.size(), "Debería haber un único hueco si no hay funciones");

        // Supongamos que tu cine abre a las 09:00 y cierra a las 23:59
        assertEquals("00:00", result.get(0).start().toLocalTime().toString());
        assertEquals("23:59", result.get(0).end().toLocalTime().toString());

        // Verificamos que el servicio realmente llamó al repositorio
        verify(showTimeRepository, times(1)).findByHallIdAndStartTimeBetween(eq(hallId),
                any(LocalDateTime.class),
                any(LocalDateTime.class));
    }

    @Test
    void whenOneShowExist_shouldReturnTwoAvailableSlots() {
        // --- ARRANGE (Preparar) ---
        Long hallId = 1L;
        LocalDate date = LocalDate.of(2026, 5, 25);
        int duration = 120;
        LocalDateTime showtimeStartTime = LocalDateTime.of(2026, 5, 25, 10, 00, 0);
        Showtime showtime = createShowtime(showtimeStartTime, duration);

        when(showTimeRepository.findByHallIdAndStartTimeBetween(eq(hallId), any(LocalDateTime.class),
                any(LocalDateTime.class)))
                .thenReturn(List.of(showtime));

        // --- ACT (Actuar / Ejecutar) ---
        HallScheduleResponse response = showtimeService.getHallScheduling(hallId, date);
        List<AvailableSlot> result = response.availableSlots();

        // --- ASSERT (Verificar) ---
        assertNotNull(result);
        assertEquals(2, result.size(), "Debería haber un único hueco si no hay funciones");

        // Supongamos que tu cine abre a las 09:00 y cierra a las 23:59
        assertEquals("00:00", result.get(0).start().toLocalTime().toString());
        assertEquals("10:00", result.get(0).end().toLocalTime().toString());

        assertEquals("12:15", result.get(1).start().toLocalTime().toString());
        assertEquals("23:59", result.get(1).end().toLocalTime().toString());

        // Verificamos que el servicio realmente llamó al repositorio
        verify(showTimeRepository, times(1)).findByHallIdAndStartTimeBetween(eq(hallId),
                any(LocalDateTime.class),
                any(LocalDateTime.class));
    }

    @Test
    void whenManyShowExist_shouldReturnCorrespondAvailableSlots() {
        Long hallId = 1L;
        LocalDate date = LocalDate.of(2026, 5, 25);
        int duration = 120;
        LocalDateTime showtimeStartTime = LocalDateTime.of(2026, 5, 25, 10, 00, 0);
        Showtime showtime = createShowtime(showtimeStartTime, duration);
        duration = 120;
        showtimeStartTime = LocalDateTime.of(2026, 5, 25, 12, 15, 0);
        Showtime showtime2 = createShowtime(showtimeStartTime, duration);
        when(showTimeRepository.findByHallIdAndStartTimeBetween(eq(hallId), any(LocalDateTime.class),
                any(LocalDateTime.class)))
                .thenReturn(List.of(showtime, showtime2));

        // --- ACT (Actuar / Ejecutar) ---
        HallScheduleResponse response = showtimeService.getHallScheduling(hallId, date);
        List<AvailableSlot> result = response.availableSlots();

        // --- ASSERT (Verificar) ---
        assertNotNull(result);
        assertEquals(2, result.size(), "Debería haber un único hueco si no hay funciones");

        // Supongamos que tu cine abre a las 09:00 y cierra a las 23:59
        assertEquals("00:00", result.get(0).start().toLocalTime().toString());
        assertEquals("10:00", result.get(0).end().toLocalTime().toString());

        assertEquals("14:30", result.get(1).start().toLocalTime().toString());
        assertEquals("23:59", result.get(1).end().toLocalTime().toString());

        // Verificamos que el servicio realmente llamó al repositorio
        verify(showTimeRepository, times(1)).findByHallIdAndStartTimeBetween(eq(hallId),
                any(LocalDateTime.class),
                any(LocalDateTime.class));
    }

    private Showtime createShowtime(LocalDateTime start, int duration) {
        Movie m = new Movie();
        m.setDuration(duration);
        Showtime s = new Showtime();
        s.setStartTime(start);
        s.setMovie(m);
        return s;
    }
}
