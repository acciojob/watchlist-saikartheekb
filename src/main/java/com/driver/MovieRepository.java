package com.driver;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Component
public class MovieRepository {
    Map<String, Movie> movieMap = new HashMap<>();
    Map<String, List<String>> movieDirectorMap = new HashMap<>();
    Map<String, Director> directorMap = new HashMap<>();

    public void addMovieIntoDB(Movie movie){
        movieMap.put(movie.getName(), movie);
    }

    public void addDirectorIntoDB(Director director){
        directorMap.put(director.getName(), director);
    }

    public void addMovieDirectorPairIntoDB(String movieName, String directorName) {
        if(movieDirectorMap.containsKey(directorName)){
            movieDirectorMap.get(directorName).add(movieName);
        } else {
            List<String> moviesList = new ArrayList<>();
            moviesList.add(movieName);
            movieDirectorMap.put(directorName, moviesList);
        }
    }

    public Movie getMovie(String name){
        return movieMap.get(name);
    }

    public Director getDirector(String name) {
        return directorMap.get(name);
    }

    public List<String> getMoviesByDirector(String directorName){
        return movieDirectorMap.get(directorName);
    }

    public void deleteDirector(String name) {
        directorMap.remove(name);
        List<String> movieList = movieDirectorMap.get(name);
        for(String movie: movieList){
            movieMap.remove(movie);
        }
        movieDirectorMap.remove(name);
    }

    public void deleteAllDirectors() {
        Set<String> movieSet = new HashSet<>();

        for(String director: movieDirectorMap.keySet())
            movieSet.addAll(movieDirectorMap.get(director));

        for(String movie: movieSet)
            movieMap.remove(movie);

        directorMap.clear();
        movieDirectorMap.clear();
    }
}
