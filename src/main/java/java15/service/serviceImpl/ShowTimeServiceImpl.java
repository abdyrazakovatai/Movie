package java15.service.serviceImpl;

import java15.entities.Cinema;
import java15.entities.Hall;
import java15.entities.Movie;
import java15.entities.Showtime;
import java15.repository.CinemaRepository;
import java15.repository.HallRepository;
import java15.repository.MovieRepository;
import java15.repository.ShowTimeRepository;
import java15.service.ShowTimeService;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor


public class ShowTimeServiceImpl implements ShowTimeService {

    private final ShowTimeRepository showTimeRepository;

    private final MovieRepository movieRepository;

    private final HallRepository hallRepository;

    private final CinemaRepository cinemaRepository;

    @Override
    public void save(Showtime showtime) {
        showTimeRepository.save(showtime);
    }

    @Override
    public void merge(Showtime showtime) {
        showTimeRepository.merge(showtime);
    }

    @Override
    public List<Showtime> getAll() {
        return showTimeRepository.getAll();
    }

    @Override
    public Showtime findById(Long id) {
        return showTimeRepository.findById(id);
    }

    @Override
    public void saveShowTime(Long movieId,  Hall hall, Showtime showtime) {
        System.out.println("Start");
        Movie movie = movieRepository.findById(movieId);
        System.out.println("movieId = " + movieId);
        if (movie == null) {
            throw new RuntimeException("Movie not found");
        }
//        Cinema cinema = cinemaRepository.findCinemaById(cinemaId);
//        if (cinema == null) {
//            throw new RuntimeException("Cinema not found");
//        }

        System.out.println("movie = " + movie);
        System.out.println("hall = " + hall);
        System.out.println("showtime = " + showtime);
        showtime.setMovie(movie);
//        showtime.setHall(hall);
//        movie.getShowTimes().add(showtime);
//        hall.getShowTimes().add(showtime);

//        showtime.setMovie(movie);
//        showtime.setHall(hall);
//        movie.setShowTimes(List.of(showtime));
//        hall.setShowTimes(List.of(showtime));
//        hallRepository.saveForCinema(hall);
        showTimeRepository.save(showtime);

    }

    @Override
    public void saveShowTime(Long movieId, Long hallId, Showtime showtime) {
        System.out.println("Start");

        Movie movie = movieRepository.findById(movieId);
        if (movie == null) {
            throw new RuntimeException("Movie not found");
        }

//        Hall hall = hallRepository.findHallById(hallId);

//        if (hall != null){
//            Hibernate.initialize(hall.getShowTimes());
//        }else {
//            throw new RuntimeException("Hall not found");
//        }



//        showtime.setMovie(movie);
//        movie.getShowTimes().add(showtime);
//        showtime.setHall(hall);
//        hall.getShowTimes().add(showtime);

//        hall.setCinema(movie.getCinemas());

//        movie.getShowTimes().add(showtime);
//        hall.getShowTimes().add(showtime);

        showTimeRepository.merge(showtime);
    }

    @Override
    public void saveShowTime(
            Long cinemaId,
            Long movieId,
            Long hallId,
            Showtime showtime) {
        Cinema cinema = cinemaRepository.findCinemaById(cinemaId);
        if (cinema == null) {
            throw new RuntimeException("Cinema not found");
        }
        Movie movie = movieRepository.findById(movieId);
        if (movie == null) {
            throw new RuntimeException("Movie not found");
        }
        Hall hall = hallRepository.findHallById(hallId);
        if (hall == null) {
            throw new RuntimeException("Hall not found");
        }
        if (hall.getId() != null){
            hallRepository.merge(hall);
        }

        Hibernate.initialize(hall.getShowTimes());
        cinema.getHalls().add(hall);
        cinema.getMovies().add(movie);
        showtime.setMovie(movie);
        showtime.setHall(hall);
        movie.getShowTimes().add(showtime);
        hall.getShowTimes().add(showtime);
        hall.setCinema(cinema);

        showTimeRepository.save(showtime);
    }

}
