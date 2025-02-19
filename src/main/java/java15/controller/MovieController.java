package java15.controller;

import jakarta.transaction.Transactional;
import java15.entities.*;
import java15.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    private final TicketService tickerService;

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

//    @GetMapping("/addCinema")
//    public String addCinema(Model model) {
//        model.addAttribute("cinema", new Cinema());
//        return "addCinemaPage";
//    }

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

        int seatCount = hall.getSeatCount();

        hall.setSeatCount(seatCount + 1);

//        Ticket ticket = new Ticket();

//        tickerService.generateTicket(hall);


        cinema.setHalls(cinema.getHalls());
        hallService.save(hall);

//        for (int i = 0; i <=hall.getSeatCount() ; i++) {
//            Ticket ticket = new Ticket();
//            ticket.set
//        }
        cinemaService.merge(cinema);
        return "redirect:/movies/admin";
    }

    @GetMapping("/showTime")
    public String showTime(Model model) {
        List<Movie> movies = movieService.getAll();
        List<Hall> halls = hallService.getAllHalls();

        model.addAttribute("movies", movies);
        model.addAttribute("halls", halls);
        model.addAttribute("showTime", new Showtime());

        return "showTimePage";
    }
//ЁЁЁЁЁЁЁЁЁЁЁЁЁЁЁЁЁЁ
//    @PostMapping("/saveShowTime")
//    @Transactional
//    public String saveShowTime(
//            @RequestParam("movieId") Long movieId,
//            @RequestParam("hallId") Long hallId,
//            @ModelAttribute Showtime showtime
//    ) {
//
//        Movie movie = movieService.findById(movieId);
//        System.out.println("movie = " + movie);
//        Hall hall = hallService.findHallById(hallId);
//        System.out.println("hallById = " + hall);
//
//
//        Cinema cinema = hall.getCinema();
//        if (cinema == null) {
//            throw new RuntimeException("Hall is not linked to a Cinema");
//        }
//
//        // Проверить, связан ли Movie с этим Cinema
//        if (!movie.getCinemas().contains(cinema)) {
//            // Связать Movie с Cinema
//
////            ~~~~~~~~~~~~~~~~~~~~~
//
//            movie.getCinemas().add(cinema);
//            cinema.getMovies().add(movie);
//
//
//            showtime.setHall(hall);
//            hall.getShowTimes().add(showtime);
//
////            showTimeService.merge(showtime);
//
//            movie.getShowTimes().add(showtime);
//            showtime.setMovie(movie);
//
//        }
//        List<Ticket> tickets = new ArrayList<>();
//        for (int i = 1; i <= hall.getSeatCount(); i++) {
//            Ticket ticket = new Ticket();
//            System.out.println("i = " + i);
//            ticket.setSeatNumber(i);
//            ticket.setShowtime(showtime);
//            tickets.add(ticket);
//            showtime.getTickets().add(ticket);
//        }
////
////        showtime.getTickets().add(tickets.get(0));
//
//        // Сохранить обновлённые объекты
////            showTimeService.merge(showtime);
////        movieService.merge(movie);1111111111111111111111
////            hallService.merge(hall);
////            cinemaService.merge(cinema);
//
////            ~~~~~~~~~~~~~~~~~~~
//
//        hall.setCinema(cinema);
//        cinema.getHalls().add(hall);
//
//        hallService.merge(hall);
//        cinemaService.merge(cinema);
//
//        showtime.setMovie(movie);
//        showtime.setHall(hall);
////        }
//
//        showTimeService.saveShowTime(movieId, hallId, showtime);
//
//        return "redirect:/movies/admin";
//    }
//    ЁЁЁЁЁЁЁЁЁЁЁЁЁЁЁЁЁЁЁЁЁЁЁЁЁЁ

    @PostMapping("/saveShowTime")
    @Transactional
    public String saveShowTime(
            @RequestParam("movieId") Long movieId,
            @RequestParam("hallId") Long hallId,
            @ModelAttribute Showtime showtime
    ) {
        // Получение Movie и Hall
        Movie movie = movieService.findById(movieId);
        System.out.println("movie = " + movie);
        Hall hall = hallService.findHallById(hallId);
        System.out.println("hall = " + hall);

        // Проверка связи с Cinema
        Cinema cinema = hall.getCinema();
        if (cinema == null) {
            throw new RuntimeException("Hall is not linked to a Cinema");
        }

        // Установка связей между объектами
        if (!movie.getCinemas().contains(cinema)) {
            movie.getCinemas().add(cinema);
            cinema.getMovies().add(movie);
        }
        showtime.setHall(hall);
        hall.getShowTimes().add(showtime);

        showTimeService.saveShowTime(movieId, hallId, showtime);

        movie.getShowTimes().add(showtime);
        showtime.setMovie(movie);

        System.out.println("showtime = " + showtime);

        // Сохранение showtime для получения ID

        System.out.println("showtime = " + showtime);

        // Создание и добавление билетов
//        List<Ticket> tickets = new ArrayList<>();
        for (int i = 1; i <= hall.getSeatCount(); i++) {
            Ticket ticket = new Ticket();
            ticket.setSeatNumber(i);
            System.out.println("i = " + i);
            ticket.setShowtime(showtime); // Установка связи с сеансом
            showtime.getTickets().add(ticket); // Добавление билета в список сеанса
            tickerService.merge(ticket);
        }

//        showtime.getTickets().addAll(tickets);

        // Обновление сеанса с билетами
        showTimeService.merge(showtime);

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

        List<Showtime> allShowtimesForCinema = new ArrayList<>();
        for (Hall hall : cinema.getHalls()) {
            List<Showtime> showtimesForHall = showTimeService.findHallById(hall.getId());
            allShowtimesForCinema.addAll(showtimesForHall);
        }
        model.addAttribute("cinema", cinema);
        model.addAttribute("showtimes", allShowtimesForCinema);

        return "/cinemasDetailsPage";
    }

    @GetMapping("/showtime")
    public String movieDetailsInfo(@RequestParam("id") Long id, Model model) {
        Showtime showtime = showTimeService.findById(id);
//        model.addAttribute("showtimes" ,showtimes);
        System.out.println("showtime = " + showtime);
        model.addAttribute("showtime", showtime);
        return "movieDetailsInfoPage";
    }

    @GetMapping("/showTimes")
    public String showTimes(Model model) {
        List<Showtime> showtimes = showTimeService.getAll();
        model.addAttribute("showTimes", showtimes);
        return "showTimesPage";
    }

}
