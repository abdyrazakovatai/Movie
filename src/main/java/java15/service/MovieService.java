package java15.service;

import java15.entities.Movie;
import java15.entities.MovieInfo;

import java.util.List;

public interface MovieService {

    List<Movie> getAll();

    void addMovieToCinema(Movie movie,Long cinemaId);

    void save(Movie movie);

    void merge(Movie movie);

    Movie findByIdMovie(Long id);

    Movie findById(Long id);

    String save(Movie movie , MovieInfo movieInfo);

    String save(Long cinemaId, Movie movie, MovieInfo movieInfo);

    String update(String movieName,Movie movie);

    Movie getMovieByName(String movieName);

    void delete(String movieName);
}
