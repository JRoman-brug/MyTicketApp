package com.jrb.ticket_service.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
public class Hall {
    @Id
    private Long id;
    private String name;
    private int totalColumns;
    private int totalRows;

    @OneToMany
    private List<Seat> seats;
}
