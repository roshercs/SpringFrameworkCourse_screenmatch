package com.roshercs.screenmatch;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//import com.roshercs.screenmatch.main.ExampleStreams;
import com.roshercs.screenmatch.main.Main;
import com.roshercs.screenmatch.repository.SerieRepository;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner{
	@Autowired
	private SerieRepository repository;
	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//can make the first steps at beginning
		//System.out.println("Hello World!");

		Main main=new Main(repository);
		main.showMenu();
		

		//Operations with stream
		//ExampleStreams exampleStream=new ExampleStreams();
		//exampleStream.exampleStream();
		
		
		/*  REFACTOR
		//var auto detects the data type
		var consumeAPI=new ConsumeAPI();

		var json=consumeAPI.getData("https://www.omdbapi.com/?t=Game+of+Thrones&apikey=22d40db");
		System.out.println(json);

		DataConverter conversor=new DataConverter();
		var data=conversor.obtainData(json, DataSerie.class);
		System.out.println(data);

		json=consumeAPI.getData("https://www.omdbapi.com/?t=the+big+bang+theory&Season=1&Episode=1&apikey=22d40db");
		DataEpisode episode=conversor.obtainData(json, DataEpisode.class);
		System.out.println(episode);

		
		List<DataSeason> seasons=new ArrayList<>();
		for(int i=1;i<=data.seasons();i++){
			json=consumeAPI.getData("https://www.omdbapi.com/?t=the+big+bang+theory&Season="+i+"&apikey=22d40db");
			var dataSeasons=conversor.obtainData(json, DataSeason.class); 
			seasons.add(dataSeasons);
		}
		seasons.forEach(System.out::println);
		*/
	}

}
