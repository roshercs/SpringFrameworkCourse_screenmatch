package com.roshercs.screenmatch.main;

//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
//import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Optional;
//import java.util.Map;
//import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;


import com.roshercs.screenmatch.models.DataEpisode;
import com.roshercs.screenmatch.models.DataSeason;
import com.roshercs.screenmatch.models.DataSerie;
import com.roshercs.screenmatch.models.Episode;
import com.roshercs.screenmatch.models.Serie;
import com.roshercs.screenmatch.repository.SerieRepository;
import com.roshercs.screenmatch.service.ConsumeAPI;
import com.roshercs.screenmatch.service.DataConverter;

public class Main {
    private Scanner keyboard=new Scanner(System.in);
    private ConsumeAPI consumeAPI=new ConsumeAPI();
    private DataConverter conversor=new DataConverter();
    private final String url_base="https://www.omdbapi.com/?t=";
    private final String API_KEY="&apikey=22d40db";
    List<Serie> series;

    private List<DataSerie> dataSeries=new ArrayList<>();
    private SerieRepository repository;

    public Main(SerieRepository repository) {
        this.repository=repository;
        this.series=repository.findAll();
    }


    public void showMenu() {
        var option=-1;
        while(option!=0){
            var menu="""
                    1. Search Serie
                    2. Search Episode
                    3. Show Searched Series
                    0. Exit
                    """;
            System.out.println(menu);
            option=keyboard.nextInt();
            keyboard.nextLine();
            switch (option) {
                case 1:
                    searchSerie();
                    break;
                case 2:
                    searchEpisodesSerie();
                    break;
                case 3:
                    showSearchedSeries();
                    break;
                case 0:
                    System.out.println("Closing application...");
                    break;
                default:
                    System.out.println("Invalid Option");
                    break;
            }
        }
    }


    private DataSerie getDataSerie(){
        //General Series Data
        System.out.println("Please, enter the name of serie to seach: ");
        var serieName=keyboard.nextLine();
        var json=consumeAPI.getData(url_base+serieName.replace(" ","+")+API_KEY);
        var data=conversor.obtainData(json, DataSerie.class);
        //System.out.println(data);
        return data;
    }

    private void searchEpisodesSerie(){
        //DataSerie dataSerie=getDataSerie();
        System.out.println("The series currently available: ");
        showSearchedSeries();
        System.out.println("\n\n\nType the serie whose episodes you want to watch:");
        var serieName=keyboard.nextLine();

        Optional<Serie> serie=series.stream()
            .filter(s -> s.getTitle().toLowerCase().contains(serieName.toLowerCase()))
            .findFirst();
        if(!serie.isPresent()){
            System.out.println("Serie not founded...");
            return;
        }
        var dataSerie=serie.get();
        if(dataSerie.getTitle()==null){System.out.println("Serie not founded");return;}
        //Obtain Data of all Seasons
        List<DataSeason> seasons=new ArrayList<>();
        
		for(int i=1;i<=dataSerie.getSeasons();i++){
			var json=consumeAPI.getData(url_base+dataSerie.getTitle().replace(" ", "+")+"&Season="+i+API_KEY);
			var dataSeasons=conversor.obtainData(json, DataSeason.class); 
			seasons.add(dataSeasons);
		}
        //seasons.forEach(System.out::println);


        //Data modeling to a Episode List
        List<Episode> episodes= seasons.stream()
            .flatMap(s-> s.episodes().stream()
                .map(de -> new Episode(s.season(),de)))
            .collect(Collectors.toList());
        episodes.forEach(System.out::println);
        dataSerie.setEpisodes(episodes);
        repository.save(dataSerie);
        /*
        //Data Conversion to a single list Data-Episode
        List<DataEpisode> dataEpisodes=seasons.stream()
            .flatMap(s -> s.episodes().stream())
            .collect(Collectors.toList());
            //.toList()   inmutable list
        dataEpisodes.stream()
            .filter(e-> !e.evaluation().equals("N/A"))
            //.peek(e -> System.out.println("First Filter (N/A): "+e))
            .sorted(Comparator.comparing(DataEpisode::evaluation).reversed())
            //.peek(e -> System.out.println("Second Filter (M>m): "+e))
            .map(e-> e.title().toUpperCase())
            //.peek(e -> System.out.println("Third Fitler Mayusc: "+e))
            .limit(5)
            .forEach(System.out::println);
        /*
        //Episode Search by Date:
        System.out.println("Enter the year of starting point to search:");
        var year=keyboard.nextInt();
        keyboard.nextLine();

        LocalDate searchDate=LocalDate.of(year, 1, 1);
        DateTimeFormatter dtf=DateTimeFormatter.ofPattern("dd/MM/yyyy");
        episodes.stream()
            .filter(e -> e.getRealeseDate()!=null && e.getRealeseDate().isAfter(searchDate))
            .forEach(e-> System.out.println(
                "Season: "+e.getSeason()+
                "  Episode: "+e.getEpisodeNum()+
                "  Realesed Date: "+e.getRealeseDate().format(dtf)
            ));

        //Episode Search by Coincidence of Title
        System.out.println("Enter the episode title to search: ");
        var partialTitle=keyboard.nextLine();

        Optional<Episode> resultEpisode = episodes.stream()
            .filter(e -> e.getTitle().toUpperCase().contains(partialTitle.toUpperCase()))
            .findFirst();
        if(resultEpisode.isPresent()){
            System.out.println("Result: ");
            System.out.println(resultEpisode.get());
        }else{
            System.out.println("Episode not founded");
        }

        Map<Integer,Double> evaluationSeason=episodes.stream()
            .filter(e-> e.getEvaluation()>0.0)
            .collect(Collectors.groupingBy(Episode:: getSeason,Collectors.averagingDouble(Episode::getEvaluation)));
        
        System.out.println(evaluationSeason);

        DoubleSummaryStatistics sta=episodes.stream()
            .filter(e-> e.getEvaluation()>0.0)
            .collect(Collectors.summarizingDouble(Episode::getEvaluation));
        
        System.out.println(sta);
        System.out.println("Average: "+ sta.getAverage());
        System.out.println("Best Episode: "+sta.getMax());
        System.out.println("Worst Episode: "+sta.getMin());*/ 
    }

    public void searchSerie(){
        DataSerie dataSerie=getDataSerie();
        //if(dataSerie.title()==null){System.out.println("Serie not founded");return;}
        //dataSeries.add(dataSerie);
        Serie serie=new Serie(dataSerie);
        //System.out.println(serie);
        repository.save(serie);
        System.out.println(serie);
    }

    private void showSearchedSeries() {
        series=repository.findAll();
        series.stream()
            .sorted(Comparator.comparing(Serie::getGenre))
            .forEach(System.out::println);
        /*dataSeries.forEach(System.out::println);

        System.out.println("Sorted by Genre: ");
        List<Serie> series=new ArrayList<>();
        series=dataSeries.stream()
            .map(de -> new Serie(de))
            .collect(Collectors.toList());
        series.stream()
            .sorted(Comparator.comparing(Serie::getGenre))
            .forEach(System.out::println);
        */
    }
}
