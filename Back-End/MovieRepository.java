package com.example.movieist.repository;

import com.example.movieist.model.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends MongoRepository<Movie, String> {
    @Query("{'imdbId': ?0}")
    Optional<Movie> findByImdbId(String imdbId);
}