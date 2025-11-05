package com.roshercs.screenmatch.models;

public enum SerieCategory {
    ACTION("Action"),
    ROMANCE("Romance"),
    COMEDY("Comedy"),
    DRAMA("Drama"),
    CRIME("Crime");

    private String categoryOmdb;

    private SerieCategory(String categoryOmdb) {
        this.categoryOmdb = categoryOmdb;
    }
    
}
