package com.roshercs.screenmatch.models;


public enum SerieCategory {
    ACTION("Action","Accion"),
    ROMANCE("Romance","Romance"),
    COMEDY("Comedy","Comedia"),
    DRAMA("Drama","Drama"),
    CRIME("Crime","Crimen");

    private String categoryOmdb;
    private String categorySpanish;
    private SerieCategory(String categoryOmdb,String categorySpanish) {
        this.categoryOmdb = categoryOmdb;
        this.categorySpanish=categorySpanish;
    }
    
    public static SerieCategory fromString(String text){
        for(SerieCategory category: SerieCategory.values()){
            if(category.categoryOmdb.equalsIgnoreCase(text)){
                return category;
            }
        }
        throw new IllegalArgumentException("None category founded: "+text);
    }
    public static SerieCategory fromSpanish(String text){
        for(SerieCategory category: SerieCategory.values()){
            if(category.categorySpanish.equalsIgnoreCase(text)){
                return category;
            }
        }
        throw new IllegalArgumentException("None category founded: "+text);
    }
}
