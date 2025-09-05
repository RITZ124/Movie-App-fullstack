package com.example.movieist.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document(collection = "Movies")
@Data
public class Movie {
    @Id
    private String _id;
    @JsonProperty("imdbId")
    private String imdbId;
    @JsonProperty("title")
    private String title;
    @JsonProperty("releaseDate")
    private LocalDate releaseDate;
    @JsonProperty("trailerLink")
    private String trailerLink;
    @JsonProperty("genres")
    private List<String> genres;
    @JsonProperty("poster")
    private String poster;
    @JsonProperty("backdrops")
    private List<String> backdrops;
    @JsonProperty("reviewIds")
    private List<String> reviewIds;
}