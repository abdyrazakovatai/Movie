package java15.service.serviceImpl;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import java15.Enum.Role;
import java15.entities.Card;
import java15.entities.Cinema;
import java15.entities.Hall;
import java15.entities.User;
import java15.repository.CinemaRepository;
import java15.repository.HallRepository;
import java15.repository.UserRepository;
import java15.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Service
@Component
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CinemaRepository cinemaRepository;
    private final HallRepository hallRepository;

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public boolean exists(String email) {
        return userRepository.existsByName(email);
    }


    @Override
    public void saveUserWithCard(User user, Card card) {
        userRepository.saveUserWithCard(user, card);
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAllUsers();
    }

    @Override
    public void saveUser(User user) {
        userRepository.saveUser(user);
    }


    @Override
    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    @Override
    public String updateUser(String email, User user) {
        return userRepository.updateUser(email, user);
    }

    @Override
    public String deleteUser(String email) {
        return userRepository.deleteUser(email);
    }

    @Override
    public boolean validUser(String email, String password) {
        return userRepository.validUser(email, password);
//        User user = getUserByEmail(email);
//        if (!user.getPassword().equals(password)) {
//            throw new RuntimeException("Wrong password");
//        }
//        if (user.getEmail().equalsIgnoreCase("atai@gmail.com") && user.getPassword().equals("1234")){
//            return "redirect:/movies/add";
//        }
//        return "redirect:/mainPage";
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findUserById(id);
    }

    @PostConstruct
    public void saveDefaultAdmin() {
        String adminEmail = "atai@gmail.com";
        String adminPassword = "1234";
        if (!userRepository.existsByName(adminEmail)) {
            System.out.println("adminEmail = " + adminEmail);
            User admin = new User();
            admin.setName("atai");
            admin.setEmail(adminEmail);
            admin.setPassword(adminPassword);
            admin.setRole(Role.ADMIN);
            userRepository.save(admin);

            List<Cinema> cinemas = List.of(
                    new Cinema("Cosmo park","https://cinematica.kg/cinema/1"),
                    new Cinema("Bishkek park","https://cinematica.kg/uploads/cinemas/f6e80094-aa2d-4608-8421-c0e6d9cfcb31.png"),
                    new Cinema("Asia mall","https://play-lh.googleusercontent.com/h0VuxBjEmWjHZLZELWpOnP3sLN0dXqDF0FncqFAAVTAMYFQsmblSt59THRCoyxSSRgA=w1024-h500"),
                    new Cinema("Dordoi Plaza","https://cinematica.kg/uploads/cinemas/413acb05-2ed6-4f9c-8bac-f2185ac78cc3.png"),
                    new Cinema("Dordoi Motors","https://cinematica.kg/uploads/cinemas/3e6c59c6-303e-4966-b9f1-6dab81bfba09.jpeg"),
                    new Cinema("Manas","https://manascinema.com/design/export_logo.jpg")
                    );
            for (Cinema cinema : cinemas) {
                cinemaRepository.save(cinema);
//                if (!exists(cinema.getName()));
//                cinemaRepository.save(cinema);
            }
        }
    }
}