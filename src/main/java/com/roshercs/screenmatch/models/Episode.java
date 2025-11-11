package com.roshercs.screenmatch.models;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="episodes")
public class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private Integer season;
    private String title;
    private Integer episodeNum;
    private Double evaluation;
    private LocalDate realeseDate;
    @ManyToOne 
    private Serie serie;

    public Episode(){}
    public Integer getSeason() {
        return season;
    }
    public void setSeason(Integer season) {
        this.season = season;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Integer getEpisodeNum() {
        return episodeNum;
    }
    public void setEpisodeNum(Integer episodeNum) {
        this.episodeNum = episodeNum;
    }
    public Double getEvaluation() {
        return evaluation;
    }
    public void setEvaluation(Double evaluation) {
        this.evaluation = evaluation;
    }
    public LocalDate getRealeseDate() {
        return realeseDate;
    }
    public void setRealeseDate(LocalDate realeseDate) {
        this.realeseDate = realeseDate;
    }
    
    public Serie getSerie() {
        return serie;
    }
    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    public Episode(Integer season,DataEpisode data) {
        this.season = season;
        try {
            this.episodeNum=Integer.parseInt(data.episodeNum());
        } catch (NumberFormatException e) {
            this.episodeNum=-99;
        }

        try {
            this.evaluation=Double.valueOf(data.evaluation());
        } catch (NumberFormatException e) {
            this.evaluation=0.0;
        }
        this.title=data.title();
        try {
            this.realeseDate=LocalDate.parse(data.realeseDate());
        } catch (DateTimeParseException e) {
            this.realeseDate=null;
        }
    }
    @Override
    public String toString() {
        return "Episode [season=" + season + ", title=" + title + ", episodeNum=" + episodeNum + ", evaluation="
                + evaluation + ", realeseDate=" + realeseDate + "]";
    }
    
    

}
