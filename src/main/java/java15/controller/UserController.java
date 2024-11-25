package java15.controller;

import jakarta.servlet.http.HttpSession;
import java15.Enum.Role;
import java15.entities.Card;
import java15.entities.Movie;
import java15.entities.Showtime;
import java15.entities.User;
import java15.service.MovieService;
import java15.service.ShowTimeService;
import java15.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final MovieService movieService;
    private final ShowTimeService showTimeService;


    @GetMapping
    public String signUp(Model model) {
//        List<Movie> movies = movieService.getAll();
//        model.addAttribute("movies", movies);
        List<Showtime> showtimes = showTimeService.getAll();
        model.addAttribute("showtimes", showtimes);
        model.addAttribute("user", new User());
        return "/mainPage";
    }

    @GetMapping("/showTimeDetails")
    public String getMovie(@RequestParam("id") Long id,
                           Model model) {
//        List<Showtime> showtime = showTimeService.getAll();
        Showtime showtime = showTimeService.findById(id);
//        Movie movie = movieService.findByIdMovie(id);
//        System.out.println("movie = " + movie);
//        model.addAttribute("movie", movie);\
//        model.addAttribute("showtimes",showtimes);
        model.addAttribute("showtime" ,showtime);
        return "/showTimeDetailsPage";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "/loginPage";
    }

    @PostMapping("/loginValid")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password, Model model) {
        try {
//            return userService.validUser(email, password);
//
//        }catch (Exception e) {
//            model.addAttribute("user", new User());
//            return "redirect:/login";
//        }
            if (userService.validUser(email, password)) {
                User user = userService.getUserByEmail(email);
                if (user.getEmail().equalsIgnoreCase("atai@gmail.com") && user.getPassword().equalsIgnoreCase("1234")) {
                    model.addAttribute("movie", new Movie());
                    return "redirect:/movies/admin";
                } else {
                    return "/mainPage";
                }
            } else {
                model.addAttribute("error", "Invalid email or password");
                return "loginPage";
            }
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "loginPage";
        }
    }

//    @GetMapping("adminChoiceCinema")
//    public String admin(Model model) {
//        List<Cinema> cinemas = cinemaService.getAllCinemas();
////        for (Cinema cinema : cinemas) {
////            System.out.println("cinema = " + cinema);
////        }
//        model.addAttribute("cinemas", cinemas);
//        model.addAttribute("selectedCinemaId", new Long(0));
//        return "choiceCinemaPage";
//    }

//    @GetMapping("/choiceMovie")
//    public String movie(@RequestParam Long cinemaId, Model model) {
//        List<Movie> movies = movieService.getAll();
////        model.addAttribute("cinemaId", cinemaId);
//        model.addAttribute("movies", movies);
//        return "addMoviePage";
//    }


//    @PostMapping("/saveMovie")
//    public String addMovie(
//            @RequestParam(name = "movieName") String movieName,
//            @RequestParam(name = "image") String image,
//            @RequestParam(name = "genre") Genre genre,
//            @RequestParam(name = "duration") int duration,
//            @RequestParam(name = "releaseDate") String releaseDate,
//            @RequestParam(name = "ageRating") int ageRating,
//            @RequestParam(name = "thrillerUrl") String thrillerUrl,
//            @RequestParam(name = "director") String director,
//            @RequestParam(name = "actor") String actor,
//            @RequestParam(name = "country") String country,
//            @RequestParam(name = "language") String language,
//            @RequestParam(name = "description") String description,
//            @RequestParam(name = "cinemaId") Long cinemaId,
//            Model model) {
//
//        System.out.println("movieName = " + movieName);
//
//        Movie exitingMovie = movieService.getMovieByName(movieName);
//
//        System.out.println("exitingMovie.getMovieName() = " + exitingMovie.getMovieName());
//        if (exitingMovie != null){
//            model.addAttribute("message","A movie with this name already exits ");
//            return "redirect:/users/addMovie";
//        }
//
//        Movie movie = new Movie();
//        movie.setMovieName(movieName);
//        movie.setImage(image);
//        movie.setGenre(genre);
//        movie.setDuration(duration);
//        movie.setReleaseDate(LocalDate.parse(releaseDate));
//        movie.setAgeRating(ageRating);
//        movie.setThrillerUrl(thrillerUrl);
//
//        MovieInfo movieInfo = new MovieInfo();
//        movieInfo.setDirector(director);
//        movieInfo.setActor(actor);
//        movieInfo.setCountry(country);
//        movieInfo.setLanguage(language);
//        movieInfo.setDescription(description);
//        movieInfo.setMovie(movie);
//        movie.setMovieInfo(movieInfo);
//
//
//        Cinema cinema = cinemaService.findCinemaById(cinemaId);
//        System.out.println("cinema = " + cinema);
//        movie.getCinemas().add(cinema);
//        cinema.getMovies().add(movie);
//        System.out.println("movie.getCinemas() = " + movie.getCinemas());
//        System.out.println("cinema.getMovies() = " + cinema.getMovies());
//
//
//        System.out.println("movieInfo = " + movieInfo);

////        movieInfoService.saveMovieInfo(movieInfo);
//        cinemaService.saveForCinema(cinema);
//        movieService.saveForCinema(movie);
////        movieService.saveForCinema(movie);
////        movieInfoService.saveMovieWithMovieInfo(movie, movieInfo);
//        model.addAttribute("movie", movie);
//        model.addAttribute("message", "Movie added successfully");
//
//        return "redirect:/users/addMovie";
//
//    }

    @GetMapping("/register")
    public String submitCard(@ModelAttribute User user, Model model, HttpSession session) {
        User sessionUser = (User) session.getAttribute("user");
        if (sessionUser == null) {
            session.setAttribute("user", user);
        }
        model.addAttribute("user", user);
        return "registerPage";
    }

    @PostMapping("/cardSign")
    public String cardSignUp(@ModelAttribute User user, @ModelAttribute Card card, Model model, HttpSession session) {
        session.setAttribute("card", card);

        session.setAttribute("user", user);
        model.addAttribute("card", new Card());
        return "cardSignPage";
    }


    @PostMapping("/saveUserAndCard")
    public String saveCard(@ModelAttribute("user") User user, @ModelAttribute Card card, HttpSession session) {

        User user1 = (User) session.getAttribute("user");

        user1.setRole(Role.USER);
        user1.setCard(card);
        card.setUser(user1);
        userService.saveUserWithCard(user1, card);
        return "/mainPage";
    }


//
//    @GetMapping("/cosmoPark")
//    public String cosmoPark(Model model) {
//        model.addAttribute("cinema", new Cinema());
//        return "/cosmoParkPage";
//    }

//    @GetMapping("/singUp")
//    public String singUp(Model model) {
//        model.addAttribute("user", new User());
//        return "/signUpPage";

//    }
//    @PostMapping("/saveUser")
//    public String saveUser(@ModelAttribute("user") User user) {
//        userService.saveUser(user);
//        return "/mainPage";
//    }
//
//
//    @GetMapping("/getAllHalls")
//    public String getAllUsers(Model model) {
//        model.addAttribute("users",userService.getAllHalls());
//        return "/mainPage";
//    }
}