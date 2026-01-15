package com.jrb.ticket_service.repository;

import com.jrb.ticket_service.entity.Seat;
import com.jrb.ticket_service.entity.enums.TicketStatus;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
class SeatRepositoryTest {

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void testOptimisticLocking_ThrowExceptionOnConcurrentUpdate() {
        // 1. PREPARACIÓN (GIVEN)
        // Creamos un asiento inicial en estado LIBRE
        Seat seat = new Seat();
        seat.setStatus(TicketStatus.LIBRE);
        // Asegúrate de setear otros campos obligatorios si los tienes (ej: número,
        // fila)

        Seat savedSeat = seatRepository.saveAndFlush(seat);
        Long seatId = savedSeat.getSeatId();

        // Limpiamos la caché de JPA para forzar que las siguientes consultas vayan a la
        // DB real
        entityManager.clear();

        // 2. ESCENARIO DE CONCURRENCIA (WHEN)

        // Hilo A (Usuario 1): Lee el asiento
        Seat user1Seat = seatRepository.findById(seatId).orElseThrow();

        // Hilo B (Usuario 2): Lee el MISMO asiento (con la misma versión inicial)
        Seat user2Seat = seatRepository.findById(seatId).orElseThrow();

        // *** PASO CRÍTICO ***: Desconectamos los objetos para simular que viajaron al
        // front y volvieron
        entityManager.detach(user1Seat);
        entityManager.detach(user2Seat);

        // 3. EJECUCIÓN (THEN)

        // El Usuario 1 es más rápido: Cambia a BLOQUEADO y guarda.
        // Esto incrementará la versión en la base de datos (ej: de 0 a 1).
        user1Seat.setStatus(TicketStatus.BLOQUEADO);
        seatRepository.saveAndFlush(user1Seat);

        // El Usuario 2 intenta guardar su copia (que todavía cree que la versión es 0).
        user2Seat.setStatus(TicketStatus.BLOQUEADO);

        // 4. VERIFICACIÓN
        // Esperamos que falle porque la versión 0 ya no existe en la DB.
        assertThrows(ObjectOptimisticLockingFailureException.class, () -> {
            seatRepository.saveAndFlush(user2Seat);
        });

        // (Opcional) Verificar que en la DB el estado quedó como lo dejó el ganador
        // (Usuario 1)
        Seat finalSeat = seatRepository.findById(seatId).get();
        assertEquals(TicketStatus.BLOQUEADO, finalSeat.getStatus());
    }
}