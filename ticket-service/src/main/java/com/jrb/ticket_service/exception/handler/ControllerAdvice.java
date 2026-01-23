package com.jrb.ticket_service.exception.handler;

import java.util.Date;

import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jrb.ticket_service.dtos.ErrorDTO;
import com.jrb.ticket_service.exception.HallNotFound;
import com.jrb.ticket_service.exception.SeatIsReservedException;
import com.jrb.ticket_service.exception.movie.MovieNotFoundException;
import com.jrb.ticket_service.exception.showtime.ShowtimeHasColisitionException;
import com.jrb.ticket_service.exception.showtime.ShowtimeNotFoundException;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(HallNotFound.class)
    public ResponseEntity<ErrorDTO> hallNotFoundHaldler(HallNotFound ex) {
        ErrorDTO response = new ErrorDTO(ex.getMessage(), new Date());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    public ResponseEntity<ErrorDTO> missingAttributes(InvalidDataAccessApiUsageException ex) {
        ErrorDTO response = new ErrorDTO("Error body attributes", new Date());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorDTO> missmatchType(HttpMessageNotReadableException ex) {
        ErrorDTO response = new ErrorDTO("Error attributes type", new Date());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SeatIsReservedException.class)
    public ResponseEntity<ErrorDTO> seatReserve(SeatIsReservedException ex) {
        ErrorDTO response = new ErrorDTO("The seat is reserved", new Date());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Movies
    @ExceptionHandler(MovieNotFoundException.class)
    public ResponseEntity<ErrorDTO> movieNotFound(MovieNotFoundException ex) {
        ErrorDTO response = new ErrorDTO("Movie not found", new Date());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // Showtime
    @ExceptionHandler(ShowtimeNotFoundException.class)
    public ResponseEntity<ErrorDTO> showtimeNotFound(ShowtimeNotFoundException ex) {
        ErrorDTO response = new ErrorDTO("Showtime not found", new Date());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ShowtimeHasColisitionException.class)
    public ResponseEntity<ErrorDTO> showtimeHasColisition(ShowtimeHasColisitionException ex) {
        ErrorDTO response = new ErrorDTO("Has a colisition with other showtime", new Date());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
