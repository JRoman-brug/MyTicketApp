package com.jrb.ticket_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jrb.ticket_service.entity.ShowTime;

public interface ShowTimeRepository extends JpaRepository<ShowTime, Long> {

}
