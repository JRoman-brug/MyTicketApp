package com.jrb.ticket_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jrb.ticket_service.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

}
