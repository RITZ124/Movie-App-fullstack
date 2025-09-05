package com.example.movieist.service;

import com.example.movieist.model.Movie;
import com.example.movieist.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public Page<Movie> getAllMovies(Pageable pageable) {
        return movieRepository.findAll(pageable);
    }

    public Optional<Movie> getMovieById(String id) {
        return movieRepository.findById(id);
    }

    public Optional<Movie> getMovieByImdbId(String imdbId) {
        return movieRepository.findByImdbId(imdbId);
    }

    public Movie createMovie(Movie movie) {
        System.out.println("Saving to repository: " + (movie != null ? movie.toString() : "null"));
        Movie savedMovie = movieRepository.save(movie);
        System.out.println("Saved from repository: " + (savedMovie != null ? savedMovie.toString() : "null"));
        return savedMovie;
    }

    public void deleteMovie(String id) {
    }
}