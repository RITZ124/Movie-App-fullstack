package com.example.movieist.controller;

import com.example.movieist.model.Movie;
import com.example.movieist.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/Movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<Page<Movie>> getAllMovies(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Movie> movies = movieService.getAllMovies(pageable);
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable String id) {
        Optional<Movie> movie = movieService.getMovieById(id);
        return movie.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/imdb/{imdbId}")
    public ResponseEntity<Movie> getMovieByImdbId(@PathVariable String imdbId) {
        Optional<Movie> movie = movieService.getMovieByImdbId(imdbId);
        return movie.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createMovie(@RequestBody Movie movie) {
        try {
            System.out.println("Incoming movie data: " + movie);
            Movie savedMovie = movieService.createMovie(movie);
            System.out.println("Saved movie data: " + savedMovie);
            return ResponseEntity.ok(savedMovie);
        } catch (Exception e) {
            System.err.println("POST error: " + e.getMessage());
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMovie(@PathVariable String id, @RequestBody Movie movie) {
        try {
            System.out.println("Incoming update data for id " + id + ": " + movie);
            Optional<Movie> existingMovie = movieService.getMovieById(id);
            if (existingMovie.isPresent()) {
                movie.set_id(id); // Preserve the existing ID
                Movie updatedMovie = movieService.createMovie(movie); // Reuse create method to update
                System.out.println("Updated movie data: " + updatedMovie);
                return ResponseEntity.ok(updatedMovie);
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            System.err.println("PUT error: " + e.getMessage());
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable String id) {
        try {
            Optional<Movie> movie = movieService.getMovieById(id);
            if (movie.isPresent()) {
                movieService.deleteMovie(id); // Call service method
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            System.err.println("DELETE error: " + e.getMessage());
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}