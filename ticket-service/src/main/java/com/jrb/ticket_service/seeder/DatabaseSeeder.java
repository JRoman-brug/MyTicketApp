package com.jrb.ticket_service.seeder;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.jrb.ticket_service.dtos.HallDTOs;
import com.jrb.ticket_service.entity.Movie;
import com.jrb.ticket_service.repository.MovieRepository;
import com.jrb.ticket_service.service.HallService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DatabaseSeeder implements CommandLineRunner {
    private HallService hallService;
    private MovieRepository movieRepository;

    public DatabaseSeeder(HallService hallService, MovieRepository movieRepository) {
        this.hallService = hallService;
        this.movieRepository = movieRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        smallHall();
        mediumHall();
        largeHall();
        movieSeeder();
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

    public void movieSeeder() {

        if (movieRepository.count() == 0) { // Solo inserta si la tabla está vacía
            List<Movie> movies = List.of(
                    new Movie(null, "Inception", 148,
                            "https://image.tmdb.org/t/p/w500/edv5bs1pSdfS2SIsT6DwTndmdfR.jpg"),
                    new Movie(null, "The Matrix", 136,
                            "https://image.tmdb.org/t/p/w500/f89U3Y9YvYvwpovpkZZCc6uY9Ld.jpg"),
                    new Movie(null, "Interstellar", 169,
                            "https://image.tmdb.org/t/p/w500/gEU2QniE6E7oBvsku9fSnd2S3qa.jpg"),
                    new Movie(null, "Pulp Fiction", 154,
                            "https://image.tmdb.org/t/p/w500/d5iIl9h9btztU0kz5v9viCHY9uT.jpg"),
                    new Movie(null, "The Godfather", 175,
                            "https://image.tmdb.org/t/p/w500/3bhkrj0vU686S0X79R9Ma6o7m9n.jpg"),
                    new Movie(null, "Spider-Man: Across the Spider-Verse", 140,
                            "https://image.tmdb.org/t/p/w500/8Vt6mO96Jm9pjaPLgDuvL--pXIK.jpg"),
                    new Movie(null, "Parasite", 132, "https://image.tmdb.org/t/p/w500/7IiTTj0CcBSo7fulCOyiTH9szvE.jpg"),
                    new Movie(null, "The Dark Knight", 152,
                            "https://image.tmdb.org/t/p/w500/qJ2tW6WMUDp9QmSbmM949O9vBTp.jpg"),
                    new Movie(null, "Fight Club", 139,
                            "https://image.tmdb.org/t/p/w500/pB8BM79vU7vSzao6ePgoihzhZDx.jpg"),
                    new Movie(null, "Seven", 127, "https://image.tmdb.org/t/p/w500/69Sns8o3S3q6z78FkARQGAC9YS3.jpg"));
            movieRepository.saveAll(movies);
            log.info("Movies were uploaded succesfully");
        }
    }
}
