package com.jrb.ticket_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jrb.ticket_service.entity.Seat;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {

}
