package com.roshercs.screenmatch.models;

import java.util.List;
import java.util.Optional;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;


//import com.roshercs.screenmatch.service.ConsultChatGPT;

@Entity
@Table(name="series")
public class Serie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String title;
    private Integer seasons;  
    private Double evaluation;
    @Enumerated(EnumType.STRING)
    private SerieCategory genre;
    private String sinopsis;
    private String poster;
    private String actors;

    @Transient 
    private List<Episode> episodes;

    
    public Serie() {
    }

    public Serie(DataSerie dataSerie) {
        this.title=dataSerie.title();
        this.evaluation=Optional.of(Double.valueOf(dataSerie.evaluation())).orElse(0.0);
        //this.sinopsis= ConsultChatGPT.requestTraduction(dataSerie.sinopsis());
        this.sinopsis= dataSerie.sinopsis();
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

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
    
    
    
}
