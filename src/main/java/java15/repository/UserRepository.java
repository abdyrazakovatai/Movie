package java15.repository;

import jakarta.transaction.Transactional;
import java15.entities.Card;
import java15.entities.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface UserRepository {

    String saveUserWithCard(User user, Card card);

    void save(User user);

    boolean existsByName(String email);

    List<User> getAllUsers();

    void saveUser(User user);

    User getUserByEmail(String email);

    String updateUser(String email, User user);

    String deleteUser(String email);

    boolean validUser(String email, String password);

    User findUserById(Long id);
}
