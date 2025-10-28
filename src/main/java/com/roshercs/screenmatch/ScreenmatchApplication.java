package com.roshercs.screenmatch;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.roshercs.screenmatch.models.DataSerie;
import com.roshercs.screenmatch.service.ConsumeAPI;
import com.roshercs.screenmatch.service.DataConverter;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//can make the first steps at beginning
		//System.out.println("Hello World!");
		//throw new UnsupportedOperationException("Unimplemented method 'run'");
		
		//var auto detects the data type
		var consumeAPI=new ConsumeAPI();

		var json=consumeAPI.getData("https://www.omdbapi.com/?t=Game+of+Thrones&apikey=22d40db");
		System.out.println(json);

		DataConverter conversor=new DataConverter();
		var data=conversor.obtainData(json, DataSerie.class);
		System.out.println(data);
		
	}

}
