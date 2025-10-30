package com.roshercs.screenmatch.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
		seasons.forEach(System.out::println);

    }
}
