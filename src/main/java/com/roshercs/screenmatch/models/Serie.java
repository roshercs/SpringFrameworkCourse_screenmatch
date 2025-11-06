package com.roshercs.screenmatch.models;

import java.util.Optional;

import com.roshercs.screenmatch.service.ConsultChatGPT;


public class Serie {
    private String title;
    private Integer seasons; 
    private Double evaluation;
    private SerieCategory genre;
    private String sinopsis;
    private String poster;
    private String actors;
    
    public Serie(DataSerie dataSerie) {
        this.title=dataSerie.title();
        this.evaluation=Optional.of(Double.valueOf(dataSerie.evaluation())).orElse(0.0);
        this.sinopsis= ConsultChatGPT.requestTraduction(dataSerie.sinopsis());
        this.poster=dataSerie.poster();
        this.actors=dataSerie.actors();
        this.genre=SerieCategory.fromString(dataSerie.genre().split(",")[0].trim());
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getSeasons() {
        return seasons;
    }

    public void setSeasons(Integer seasons) {
        this.seasons = seasons;
    }

    public Double getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Double evaluation) {
        this.evaluation = evaluation;
    }

    public SerieCategory getGenre() {
        return genre;
    }

    public void setGenre(SerieCategory genre) {
        this.genre = genre;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    @Override
    public String toString() {
        return "[genre=" + genre +", title=" + title + ", seasons=" + seasons + ", evaluation=" + evaluation + ", sinopsis=" + sinopsis + ", poster=" + poster + ", actors=" + actors + "]";
    }
    
    
    
}
