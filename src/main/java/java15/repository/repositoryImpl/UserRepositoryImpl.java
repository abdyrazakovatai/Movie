package java15.repository.repositoryImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java15.Enum.Role;
import java15.entities.Card;
import java15.entities.User;
import java15.repository.CinemaRepository;
import java15.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final CinemaRepository cinemaRepository;

    @PersistenceContext
    private EntityManager em;

    @Override
    public String saveUserWithCard(User user, Card card) {

        user.setCard(card);
        card.setUser(user);

        em.persist(card);
        em.persist(user);
        return "success";
    }

    @Override
    public void save(User user) {
        em.persist(user);
    }

    @Override
    public boolean existsByName(String email) {
        List<User> users = getAllUsers();
        return users.stream().anyMatch(user -> user.getEmail().equals(email) && user.getRole() == Role.ADMIN);
    }

    @Override
    public List<User> getAllUsers() {
        return em.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public User getUserByEmail(String email) {
        try {
            return em.createQuery("select u from User u where u.email=:email", User.class).setParameter("email", email).getSingleResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    @Override
    public void saveUser(User user) {
        em.getTransaction().begin();
        System.out.println("user.getCard().getSumma() = " + user.getCard().getSumma());
        em.persist(user.getCard());
        em.merge(user);
        em.getTransaction().commit();

    }

    @Override
    public String updateUser(String email, User user) {
        User user1 = getUserByEmail(email);
        if (user1 != null) {
            user1.setName(user.getName());
            user1.setEmail(user.getEmail());
            user1.setPassword(user.getPassword());
            user1.setPassword(user.getPassword());
            user1.setPhoneNumber(user.getPhoneNumber());
            em.merge(user1);
            return "success";
        } else {
            return "Not found";
        }
    }

    @Override
    public String deleteUser(String email) {
        User user = getUserByEmail(email);
        if (user == null) {
            return "Not found";
        } else {
            em.remove(user);
            return "Deleted Success";
        }
    }

    @Override
    public boolean validUser(String email, String password) {
        User user = getUserByEmail(email);

        if (user == null) {
            throw new RuntimeException("User not found");
        }
        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Wrong password");
        }
        return true;
    }


    @Override
    public User findUserById(Long id) {
        try {

            em.createQuery("select u from User u where u.id =:id", User.class).setParameter("id", id).getSingleResult();
        } catch (Exception e) {
            System.out.println("e.getMessage() = " + e.getMessage());
        }
        return null;
    }
}


