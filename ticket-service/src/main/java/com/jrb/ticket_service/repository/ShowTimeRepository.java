package com.jrb.ticket_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jrb.ticket_service.entity.Showtime;

public interface ShowTimeRepository extends JpaRepository<Showtime, Long> {

}
