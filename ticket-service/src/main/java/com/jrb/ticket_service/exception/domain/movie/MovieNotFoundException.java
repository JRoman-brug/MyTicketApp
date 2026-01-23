package com.jrb.ticket_service.exception.domain.movie;

import org.springframework.http.HttpStatus;

import com.jrb.ticket_service.exception.base.BusinessException;
import com.jrb.ticket_service.exception.base.ErrorCode;

public class MovieNotFoundException extends BusinessException {
    public MovieNotFoundException(Long id) {
        super("Movie not found with ID: " + id,
                ErrorCode.MOVIE_NOT_FOUND,
                HttpStatus.NOT_FOUND);
    }

}
