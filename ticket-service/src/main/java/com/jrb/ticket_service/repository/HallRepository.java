package com.jrb.ticket_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jrb.ticket_service.entity.Hall;

@Repository
public interface HallRepository extends JpaRepository<Hall, Long> {

}
