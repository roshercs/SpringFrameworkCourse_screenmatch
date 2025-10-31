package com.roshercs.screenmatch.main;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.roshercs.screenmatch.models.DataEpisode;
import com.roshercs.screenmatch.models.DataSeason;
import com.roshercs.screenmatch.models.DataSerie;
import com.roshercs.screenmatch.service.ConsumeAPI;
import com.roshercs.screenmatch.service.DataConverter;

public class Main {
    private Scanner keyboard=new Scanner(System.in);
    private ConsumeAPI consumeAPI=new ConsumeAPI();
    private DataConverter conversor=new DataConverter();
    private final String url_base="https://www.omdbapi.com/?t=";
    private final String API_KEY="&apikey=22d40db";
    
    public void showMenu() {
        //General Series Data
        System.out.println("Please, enter the name of serie to seach: ");
        var serieName=keyboard.nextLine();
        var json=consumeAPI.getData(url_base+serieName.replace(" ","+")+API_KEY);
        var data=conversor.obtainData(json, DataSerie.class);
        System.out.println(data);

        //Obtain Data of all Seasons
        List<DataSeason> seasons=new ArrayList<>();
		for(int i=1;i<=data.seasons();i++){
			json=consumeAPI.getData(url_base+serieName.replace(" ", "+")+"&Season="+i+API_KEY);
			var dataSeasons=conversor.obtainData(json, DataSeason.class); 
			seasons.add(dataSeasons);
		}
		//seasons.forEach(System.out::println);

        //Only print Episodes Title of each Season, clasic solution
        for (int i = 0; i < data.seasons(); i++) {
            System.out.println("Season "+(i+1)+": ");
            List<DataEpisode> episodesSeasons=seasons.get(i).episodes();
            for (int j = 0; j < episodesSeasons.size(); j++) {
                System.out.println("\t"+episodesSeasons.get(j).title());
            }
        }
        System.out.println("----------------------------------");
        //Only print Episodes Title of each Season, lanbda functions solution
        //seasons.forEach(season -> {System.out.println("Season "+season.season()+":"); season.episodes().forEach(episode -> System.out.println("\t"+episode.title()));});

        //Data Conversion to a single list Data-Episode
        List<DataEpisode> dataEpisodes=seasons.stream()
            .flatMap(s -> s.episodes().stream())
            .collect(Collectors.toList());
            //.toList()   inmutable list
        dataEpisodes.stream()
            .filter(e-> !e.evaluation().equals("N/A"))
            .sorted(Comparator.comparing(DataEpisode::evaluation).reversed())
            .limit(5)
            .forEach(System.out::println);
            
        

    }
}
