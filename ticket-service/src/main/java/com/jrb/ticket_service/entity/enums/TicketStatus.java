package com.jrb.ticket_service.entity.enums;

public enum TicketStatus {
    PENDING, // Reservado temporalmente (bloquea el asiento)
    CONFIRMED, // Pago realizado con éxito
    CANCELLED // Reserva expirada o cancelada por el usuario
}
