package com.jrb.ticket_service.exception.domain.showtime;

import org.springframework.http.HttpStatus;

import com.jrb.ticket_service.exception.base.BusinessException;
import com.jrb.ticket_service.exception.base.ErrorCode;

public class ShowtimeScheduleConflictException extends BusinessException {

    public ShowtimeScheduleConflictException() {
        super("Schedule collision detected for the selected hall and time.", ErrorCode.SHOWTIME_SCHEDULE_CONFLICT,
                HttpStatus.CONFLICT);

    }
}
