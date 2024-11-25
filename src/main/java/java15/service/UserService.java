package java15.service;

import jakarta.transaction.Transactional;
import java15.entities.Card;
import java15.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public interface UserService {

    void save(User user);

    boolean exists(String email);

    void saveUserWithCard(User user, Card card);

    List<User> getAll();

    void saveUser(User user);

    User getUserByEmail(String email);

    String updateUser(String email, User user);

    String deleteUser(String email);

    boolean validUser(String email, String password);

    User findUserById(Long id);
}
