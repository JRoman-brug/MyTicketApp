package com.jrb.ticket_service.entity;

import com.jrb.ticket_service.entity.enums.TicketStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Seat {

    @Id
    private long id;

    private int column;
    private int row;
    private String label;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TicketStatus status;

    @ManyToOne
    private Hall hall;

}
