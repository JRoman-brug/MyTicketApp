package com.jrb.ticket_service.seeder;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.jrb.ticket_service.dtos.HallDTOs;
import com.jrb.ticket_service.service.HallService;

@Component
public class DatabaseSeeder implements CommandLineRunner {
    private HallService hallService;

    public DatabaseSeeder(HallService hallService) {
        this.hallService = hallService;
    }

    @Override
    public void run(String... args) throws Exception {
        smallHall();
        mediumHall();
        largeHall();
    }

    public void smallHall() {
        String label = "Sala VIP Pequeña";
        int rows = 3;
        int columns = 3;
        List<String> rowLabels = List.of("A", "B", "C");
        List<String> columnLabels = List.of("1", "2", "3");
        List<List<String>> schema = List.of(
                List.of("X", "X", "X"),
                List.of("X", "X", "X"),
                List.of("X", "X", "X"));

        HallDTOs.CreateRequest request = new HallDTOs.CreateRequest(label, rows, columns, rowLabels, columnLabels,
                schema);
        hallService.createHall(request);
    }

    public void mediumHall() {
        String label = "Sala Estándar";
        int rows = 4;
        int columns = 5;
        List<String> rowLabels = List.of("A", "B", "C", "D");
        List<String> columnLabels = List.of("1", "2", "3", "4", "5");
        List<List<String>> schema = List.of(
                List.of("X", "X", "_", "X", "X"),
                List.of("X", "X", "_", "X", "X"),
                List.of("X", "X", "_", "X", "X"),
                List.of("X", "X", "_", "X", "X"));
        HallDTOs.CreateRequest request = new HallDTOs.CreateRequest(label, rows, columns, rowLabels, columnLabels,
                schema);
        hallService.createHall(request);
    }

    public void largeHall() {
        String label = "Sala IMAX";
        int rows = 5;
        int columns = 7;
        List<String> rowLabels = List.of("A", "B", "C", "D", "E");
        List<String> columnLabels = List.of("1", "2", "3", "4", "5", "6", "7");
        List<List<String>> schema = List.of(
                List.of("_", "_", "X", "X", "X", "_", "_"),
                List.of("_", "X", "X", "X", "X", "X", "_"),
                List.of("X", "X", "X", "X", "X", "X", "X"),
                List.of("X", "X", "X", "X", "X", "X", "X"),
                List.of("X", "X", "X", "X", "X", "X", "X"));
        HallDTOs.CreateRequest request = new HallDTOs.CreateRequest(label, rows, columns, rowLabels, columnLabels,
                schema);
        hallService.createHall(request);
    }
}
