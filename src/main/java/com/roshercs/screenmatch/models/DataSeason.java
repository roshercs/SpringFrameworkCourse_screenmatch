package com.roshercs.screenmatch.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public record DataSeason(
    @JsonAlias("Season") Integer season,
    @JsonAlias("Episodes") List<DataEpisode> episodes
) {

}
