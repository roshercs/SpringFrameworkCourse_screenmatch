package com.roshercs.screenmatch.main;

import java.util.Arrays;
import java.util.List;

public class ExampleStreams {
    public void exampleStream(){
        List<String> names=Arrays.asList("Brenda","Luis","Maria","Erick","Genesys","Leopoldo");

        names.stream()
            .sorted()
            .filter(n -> n.startsWith("L"))
            .map(n -> n.toUpperCase())
            .limit(3)
            .forEach(System.out::println);
    }
}
