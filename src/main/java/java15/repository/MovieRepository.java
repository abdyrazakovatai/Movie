package java15.repository;

import java15.entities.Movie;

import java.util.List;

public interface MovieRepository {

    List<Movie> getAll();

    void merge(Movie movie);

    void save(Movie movie);

    String update(String movieName, Movie movie);

    Movie findById(Long id);

    Movie findByIdMovie(Long id);

    Movie findMovieByName(String movieName);

    void delete(String movieName);

}
