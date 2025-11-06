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
    
    public static SerieCategory fromString(String text){
        for(SerieCategory category: SerieCategory.values()){
            if(category.categoryOmdb.equalsIgnoreCase(text)){
                return category;
            }
        }
        throw new IllegalArgumentException("None category founded: "+text);
    }
}
