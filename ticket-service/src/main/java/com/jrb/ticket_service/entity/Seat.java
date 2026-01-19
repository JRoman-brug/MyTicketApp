package com.jrb.ticket_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Version;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seatId;

    @Version
    private int version;

    @Column(name = "seat_row")
    private int row;
    @Column(name = "seat_column")
    private int column;
    private String label;

    @ManyToOne
    @JoinColumn(name = "hall_id", referencedColumnName = "hallId")
    private Hall hall;
}
