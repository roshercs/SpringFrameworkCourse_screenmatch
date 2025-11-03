package com.roshercs.screenmatch.models;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;


public class Episode {
    private Integer season;
    private String title;
    private Integer episodeNum;
    private Double evaluation;
    private LocalDate realeseDate;
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
