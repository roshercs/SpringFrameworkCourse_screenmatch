package com.roshercs.screenmatch.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataSerie(
    //JsonAlias (only lecture) indicates the true name of variable in the Json, an Alternative is @JsonProperty("") (lecture and write)
    @JsonAlias("Title") String title,
    @JsonAlias("totalSeasons") Integer seasons, 
    @JsonAlias("imdbRating") String evaluation,
    @JsonAlias("Genre") String genre,
    @JsonAlias("Plot") String sinopsis,
    @JsonAlias("Poster") String poster,
    @JsonAlias("Actors") String actors) {
}
