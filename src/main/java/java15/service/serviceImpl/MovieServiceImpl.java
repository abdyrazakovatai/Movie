package java15.service.serviceImpl;

import java15.entities.Cinema;
import java15.entities.Movie;
import java15.entities.MovieInfo;
import java15.repository.CinemaRepository;
import java15.repository.MovieRepository;
import java15.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final CinemaRepository cinemaRepository;

    @Override
    public List<Movie> getAll() {
        return movieRepository.getAll();
    }

    @Override
    public void addMovieToCinema(Movie movie, Long cinemaId) {
        Cinema cinema = cinemaRepository.findCinemaById(cinemaId);

        movieRepository.save(movie);

        cinema.getMovies().add(movie);
        cinemaRepository.save(cinema);
    }

    @Override
    public void save(Movie movie) {
        movieRepository.save(movie);
    }

    @Override
    public void merge(Movie movie) {
        movieRepository.save(movie);
    }

    @Override
    public Movie findByIdMovie(Long id) {
        return movieRepository.findByIdMovie(id);
    }

    @Override
    public Movie findById(Long id) {
        return movieRepository.findById(id);
    }

    @Override
    public String save(Movie movie, MovieInfo movieInfo) {
        movie.setMovieInfo(movieInfo);
        movieInfo.setMovie(movie);
        movieRepository.save(movie);
        return "Success";
    }

    @Override
    @Transactional
    public String save(Long cinemaId, Movie movie, MovieInfo movieInfo) {
        System.out.println("Start");
        Cinema cinema = cinemaRepository.findCinemaById(cinemaId);
        if (cinema == null) return "Cinema not found";

        System.out.println("cinema = " + cinema);
        System.out.println("movie = " + movie);
        System.out.println("movieInfo = " + movieInfo);
        movie.setMovieInfo(movieInfo);
        movieInfo.setMovie(movie);
        cinema.getMovies().add(movie);
        movie.getCinemas().add(cinema);
        movieRepository.save(movie);
        return "Successfully saved";
    }

    @Override
    public String update(String movieName, Movie movie) {
        return movieRepository.update(movieName, movie);
    }

    @Override
    public Movie getMovieByName(String movieName) {
        return movieRepository.findMovieByName(movieName);
    }

    @Override
    public void delete(String movieName) {
        movieRepository.delete(movieName);
    }
}
