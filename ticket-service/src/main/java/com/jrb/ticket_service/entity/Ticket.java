package com.jrb.ticket_service.entity;

import com.jrb.ticket_service.entity.enums.TicketStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
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
@Table(name = "ticket", uniqueConstraints = {
                @UniqueConstraint(name = "uk_seat_showtime", columnNames = {
                                "seat_id",
                                "showtime_id"
                })
})
public class Ticket {

        @Id
        private Long id;

        @ManyToOne
        @JoinColumn(name = "seat_id")
        private Seat seat;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "showtime_id")
        private Showtime showtime;

        @Enumerated(EnumType.STRING)
        private TicketStatus status;
        private Long userId;
}
