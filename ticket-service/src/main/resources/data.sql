-- 1. INSERTAR SALAS (HALLS)
INSERT INTO hall (name, total_rows, total_columns) VALUES ('Sala VIP Pequeña', 3, 3); -- ID 1
INSERT INTO hall (name, total_rows, total_columns) VALUES ('Sala Estándar', 4, 5);     -- ID 2
INSERT INTO hall (name, total_rows, total_columns) VALUES ('Sala IMAX', 5, 7);         -- ID 3

-- 2. INSERTAR ASIENTOS (SEATS)
-- SALA VIP PEQUEÑA (3x3, todos son 'X')
-- Fila A (0)
INSERT INTO seat (seat_row, seat_column, label, hall_id, version) VALUES (0, 0, 'A1', 1, 0), (0, 1, 'A2', 1, 0), (0, 2, 'A3', 1, 0);
-- Fila B (1)
INSERT INTO seat (seat_row, seat_column, label, hall_id, version) VALUES (1, 0, 'B1', 1, 0), (1, 1, 'B2', 1, 0), (1, 2, 'B3', 1, 0);
-- Fila C (2)
INSERT INTO seat (seat_row, seat_column, label, hall_id, version) VALUES (2, 0, 'C1', 1, 0), (2, 1, 'C2', 1, 0), (2, 2, 'C3', 1, 0);

-- SALA ESTÁNDAR (4x5, pasillo en columna 2 '_')
-- Solo insertamos donde había 'X' en tu esquema
INSERT INTO seat (seat_row, seat_column, label, hall_id, version) VALUES 
(0, 0, 'A1', 2, 0), (0, 1, 'A2', 2, 0), (0, 3, 'A4', 2, 0), (0, 4, 'A5', 2, 0),
(1, 0, 'B1', 2, 0), (1, 1, 'B2', 2, 0), (1, 3, 'B4', 2, 0), (1, 4, 'B5', 2, 0),
(2, 0, 'C1', 2, 0), (2, 1, 'C2', 2, 0), (2, 3, 'C4', 2, 0), (2, 4, 'C5', 2, 0),
(3, 0, 'D1', 2, 0), (3, 1, 'D2', 2, 0), (3, 3, 'D4', 2, 0), (3, 4, 'D5', 2, 0);

-- SALA IMAX (5x7, forma trapezoidal)
-- Fila A (Solo centro)
INSERT INTO seat (seat_row, seat_column, label, hall_id, version) VALUES (0, 2, 'A3', 3, 0), (0, 3, 'A4', 3, 0), (0, 4, 'A5', 3, 0);
-- Fila B
INSERT INTO seat (seat_row, seat_column, label, hall_id, version) VALUES (1, 1, 'B2', 3, 0), (1, 2, 'B3', 3, 0), (1, 3, 'B4', 3, 0), (1, 4, 'B5', 3, 0), (1, 5, 'B6', 3, 0);
-- Filas C, D, E (Completas)
INSERT INTO seat (seat_row, seat_column, label, hall_id, version) VALUES 
(2, 0, 'C1', 3, 0), (2, 1, 'C2', 3, 0), (2, 2, 'C3', 3, 0), (2, 3, 'C4', 3, 0), (2, 4, 'C5', 3, 0), (2, 5, 'C6', 3, 0), (2, 6, 'C7', 3, 0),
(3, 0, 'D1', 3, 0), (3, 1, 'D2', 3, 0), (3, 2, 'D3', 3, 0), (3, 3, 'D4', 3, 0), (3, 4, 'D5', 3, 0), (3, 5, 'D6', 3, 0), (3, 6, 'D7', 3, 0),
(4, 0, 'E1', 3, 0), (4, 1, 'E2', 3, 0), (4, 2, 'E3', 3, 0), (4, 3, 'E4', 3, 0), (4, 4, 'E5', 3, 0), (4, 5, 'E6', 3, 0), (4, 6, 'E7', 3, 0);

-- 3. SEED DE MOVIES (9 Películas)
INSERT INTO movie (name, duration, poster_url) VALUES ('Inception', 148, 'https://img.com/inception.jpg');
INSERT INTO movie (name, duration, poster_url) VALUES ('Interstellar', 169, 'https://img.com/interstellar.jpg');
INSERT INTO movie (name, duration, poster_url) VALUES ('The Dark Knight', 152, 'https://img.com/darkknight.jpg');
INSERT INTO movie (name, duration, poster_url) VALUES ('Pulp Fiction', 154, 'https://img.com/pulpfiction.jpg');
INSERT INTO movie (name, duration, poster_url) VALUES ('The Matrix', 136, 'https://img.com/matrix.jpg');
INSERT INTO movie (name, duration, poster_url) VALUES ('Gladiator', 155, 'https://img.com/gladiator.jpg');
INSERT INTO movie (name, duration, poster_url) VALUES ('Avatar', 162, 'https://img.com/avatar.jpg');
INSERT INTO movie (name, duration, poster_url) VALUES ('The Prestige', 130, 'https://img.com/prestige.jpg');
INSERT INTO movie (name, duration, poster_url) VALUES ('Memento', 113, 'https://img.com/memento.jpg');

-- 4. SEED DE SHOWTIMES (6 Funciones)
-- Distribuimos las funciones en la Sala 1 (Small) y Sala 2 (Medium) para probar disponibilidad
-- Sala 1 (ID: 1) tiene 3 funciones: mañana, tarde y noche
INSERT INTO showtime (start_time, movie_id, hall_id) VALUES ('2026-05-25 09:00:00', 1, 1);
INSERT INTO showtime (start_time, movie_id, hall_id) VALUES ('2026-05-25 14:00:00', 2, 1);
INSERT INTO showtime (start_time, movie_id, hall_id) VALUES ('2026-05-25 20:00:00', 3, 1);

-- Sala 2 (ID: 2) tiene 2 funciones seguidas
INSERT INTO showtime (start_time, movie_id, hall_id) VALUES ('2026-05-25 12:00:00', 4, 2);
INSERT INTO showtime (start_time, movie_id, hall_id) VALUES ('2026-05-25 16:00:00', 5, 2);

-- Sala 3 (ID: 3) tiene 1 función estelar
INSERT INTO showtime (start_time, movie_id, hall_id) VALUES ('2026-05-25 21:30:00', 7, 3);