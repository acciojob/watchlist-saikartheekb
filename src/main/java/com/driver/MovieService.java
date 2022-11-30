package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Component
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public void addMovieToDB(Movie movie){
        movieRepository.addMovieIntoDB(movie);
    }

    public void addDirectorToDB(Director director) {
        movieRepository.addDirectorIntoDB(director);
    }

    public void addMovieDirectorPairToDB(String movieName, String directorName) {
        if(movieRepository.movieMap.containsKey(movieName) &&
        movieRepository.directorMap.containsKey(directorName))
            movieRepository.addMovieDirectorPairIntoDB(movieName, directorName);
    }

    public Movie getMovie(String name){
        return movieRepository.getMovie(name);
    }

    public Director getDirector(String name) {
        return movieRepository.getDirector(name);
    }

    public List<String> getMoviesByDirectorName(String directorName) {
        return movieRepository.getMoviesByDirector(directorName);
    }

    public List<String> getAllMovies() {
        return new ArrayList<>(movieRepository.movieMap.keySet());
    }

    public void deleteDirector(String name) {
        movieRepository.deleteDirector(name);
    }

    public void deleteAllDirectors() {
        movieRepository.deleteAllDirectors();
    }
}
