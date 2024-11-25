package java15.controller;

import jakarta.transaction.Transactional;
import java15.entities.*;
import java15.service.CinemaService;
import java15.service.HallService;
import java15.service.MovieService;
import java15.service.ShowTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


@Controller
@Component
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;
    private final CinemaService cinemaService;
    private final ShowTimeService showTimeService;
    private final HallService hallService;

    @GetMapping("/admin")
    public String listMovie(Model model) {
        List<Movie> movies = movieService.getAll();
        List<Hall> halls = hallService.getAllHalls();
        List<Cinema> cinemas = cinemaService.getAllCinemas();
        model.addAttribute("movies", movies);
        model.addAttribute("cinemas", cinemas);
        model.addAttribute("halls", halls);
//        model.addAttribute("movie", new Movie());
//        model.addAttribute("movieInfo", new MovieInfo());
        return "adminPage";
    }

    @GetMapping("/addCinema")
    public String addCinema(Model model) {
        model.addAttribute("cinema", new Cinema());
        return "addCinemaPage";
    }

    @GetMapping("/add")
    public String addMovie(Model model) {
//        List<Cinema> cinemas = cinemaService.getAllCinemas();
//        model.addAttribute("cinemas", cinemas);
        model.addAttribute("movie", new Movie());
        model.addAttribute("movieInfo", new MovieInfo()
        );
        return "addMoviePage";
    }

    @PostMapping("/save")
    String saveMovie(@ModelAttribute("movie") Movie movie,
                     @ModelAttribute("movieInfo") MovieInfo movieInfo
//                     @RequestParam("cinemaId") Long cinemaId
    ) {
        movieService.save(movie, movieInfo);
//        movieService.save(cinemaId, movie, movieInfo);
        return "redirect:/movies/admin";
    }

    @GetMapping("addHall")
    public String addHall(Model model) {
        List<Cinema> cinemas = cinemaService.getAllCinemas();
        model.addAttribute("cinemas", cinemas);
        model.addAttribute("hall", new Hall());
        return "addHallPage";
    }

    @PostMapping("/saveHall")
    public String saveHall(
            @RequestParam("cinemaId") Long cinemaId,
            Hall hall) {
        Cinema cinema = cinemaService.findCinemaById(cinemaId);
        hall.setCinema(cinema);
        cinema.getHalls().add(hall);
        cinema.setHalls(cinema.getHalls());
        hallService.save(hall);
        cinemaService.merge(cinema);
        return "redirect:/movies/admin";
    }

    @GetMapping("/showTime")
    public String showTime(Model model) {
//        List<Cinema> cinemas = cinemaService.getAllCinemas();

        List<Movie> movies = movieService.getAll();

        List<Hall> halls = hallService.getAllHalls();
//        for (Movie movie : movies) {
//            System.out.println("movie = " + movie);
//        }
        System.out.println("movies = " + movies);
        for (Movie movie : movies) {
            System.out.println("movie = " + movie);
        }
//        List<Hall> halls = ha
        model.addAttribute("movies", movies);
        model.addAttribute("halls", halls);
//        model.addAttribute("cinemas", cinemas);
//        model.addAttribute("halls", halls);
//        model.addAttribute("hall", new Hall());
        model.addAttribute("showTime", new Showtime());

        return "showTimePage";
    }

    @PostMapping("/saveShowTime")
    @Transactional
    public String saveShowTime(
            @RequestParam("movieId") Long movieId,
            @RequestParam("hallId") Long hallId,
            @ModelAttribute Showtime showtime
    ) {
        Movie movie = movieService.findById(movieId);
        System.out.println("movie = " + movie);
        Hall hall = hallService.findHallById(hallId);
        System.out.println("hallById = " + hall);


        Cinema cinema = hall.getCinema();
        if (cinema == null) {
            throw new RuntimeException("Hall is not linked to a Cinema");
        }

        // Проверить, связан ли Movie с этим Cinema
        if (!movie.getCinemas().contains(cinema)) {
            // Связать Movie с Cinema
//            ~~~~~~~~~~~~~~~~~~~~~
            movie.getCinemas().add(cinema);
            cinema.getMovies().add(movie);


            showtime.setHall(hall);
            hall.getShowTimes().add(showtime);

            movie.getShowTimes().add(showtime);
            showtime.setMovie(movie);

            // Сохранить обновлённые объекты
//            showTimeService.merge(showtime);
            movieService.merge(movie);
//            hallService.merge(hall);
//            cinemaService.merge(cinema);

//            ~~~~~~~~~~~~~~~~~~~
        }




            hall.setCinema(cinema);
            cinema.getHalls().add(hall);

            hallService.merge(hall);
            cinemaService.merge(cinema);

            showtime.setMovie(movie);
            showtime.setHall(hall);
//        }

        showTimeService.saveShowTime(movieId, hallId, showtime);
//        showTimeService.saveShowTime(cinemaId, movieId, hallId, showtime);
        return "redirect:/movies/admin";
    }

    @GetMapping("/cinemas")
    public String cinemas(Model model) {
        List<Cinema> cinemas = cinemaService.getAllCinemas();
        for (Cinema cinema : cinemas) {
            System.out.println("cinema = " + cinema);
        }
        model.addAttribute("cinemas", cinemas);
        return "/cinemasPage";
    }


    @GetMapping("/cinemasDetails")
    public String getMovie(@RequestParam("id") Long id,
                           Model model) {
        Cinema cinema = cinemaService.findByIdWithDetails(id);
        System.out.println("cinema = " + cinema);
        model.addAttribute("cinema", cinema);
//        cinema.getHalls().forEach(hall -> hall.getShowTimes().forEach(showtime -> showtime.getMovie().getMovieName()));
        return "/cinemasDetailsPage";
    }

}
