package com.roshercs.screenmatch.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.roshercs.screenmatch.models.Episode;
import com.roshercs.screenmatch.models.Serie;
import com.roshercs.screenmatch.models.SerieCategory;


public interface SerieRepository extends JpaRepository<Serie,Long>{
    Optional<Serie> findBytitleContainsIgnoreCase(String serieName);
    List<Serie> findTop5ByOrderByEvaluationDesc();
    List<Serie> findByGenre(SerieCategory category);
    List<Serie> findBySeasonsGreaterThanAndEvaluationGreaterThan(Integer seasons,Double evaluation);
    //SQL native
    //@Query(value = "select * from series where series.seasons <=6 AND series.evaluation >=7.5",nativeQuery = true)
    //List<Serie> seriesBySeasonsAndEvaluation();
    //JPQL
    @Query(value = "select s from Serie s where s.seasons <= :seasons AND s.evaluation >= :evaluation",nativeQuery = false)
    List<Serie> seriesBySeasonsAndEvaluation(int seasons,Double evaluation);

    @Query("select e from Serie s join s.episodes e WHERE e.title ILIKE %:episodeName%")
    List<Episode> episodesByName(String episodeName); 
    @Query("select e from Serie s join s.episodes e where s=:serie order by e.evaluation desc limit 5")
    List<Episode> top5EpisodesofSerie(Serie serie);
}
