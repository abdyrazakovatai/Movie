package java15.repository.repositoryImpl;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java15.entities.Cinema;
import java15.entities.Movie;
import java15.repository.CinemaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@Transactional
@RequiredArgsConstructor
public class CinemaRepositoryImpl implements CinemaRepository {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public void save(Cinema cinema) {
        if (cinema.getId() == null){
            em.persist(cinema);
        }else {
            em.merge(cinema);
        }
//        Cinema existingCinema = findByName(cinema.getName());
//        if (existingCinema != null) {
//            em.merge(cinema);
//        } else {
//        }
//        boolean exists = exists(cinema.getName());
//        if (exists) {
//            em.merge(cinema);
//        }
//        em.persist(cinema);
    }

    @Override
    public void merge(Cinema cinema) {
        em.merge(cinema);
    }

    @Override
    public void existsByName(List<Cinema> cinemas) {
        List<String> existingCinemaNames = cinemas.stream().map(Cinema::getName).collect(Collectors.toList());

        for (Cinema cinema : cinemas) {
            boolean exists = exists(cinema.getName());
            if (!exists(cinema.getName())) {
                em.merge(cinema);
            } else {
                em.persist(cinema);
            }
//            if (!exists) {
//                save(cinema);
//                System.out.println("Cinema '" + cinema.getName() + "' has been added");
//            }else {
//                System.out.println("Cinema '" + cinema.getName() + "' already exists");
//            }

//            if (existingCinemaNames.contains(cinema.getName())) {
//                saveForCinema(cinema);
//                System.out.println("Cinema '" + cinema.getName() + "' has been added");
//            }
//            else {
//                System.out.println("Cinema '" + cinema.getName() + "already exists");
//            }
        }
    }


    @Override
    public List<Cinema> getAllCinemas() {
        return em.createQuery("SELECT c FROM Cinema c", Cinema.class).getResultList();
    }

    @Override
    public Cinema findByName(String name) {
        try {

            return em.createQuery("select c from Cinema c where c.name = :name", Cinema.class)
                    .setParameter("name",name)
                    .getSingleResult();
        }catch (Exception e){
            System.out.println("e.getMessage() = " + e.getMessage());
        }
        return null;
    }

    @Override
    public void saveAll(List<Cinema> cinemas) {
        em.persist(cinemas);
    }

    @Override
    public Cinema findCinemaById(Long id) {
        return em.find(Cinema.class, id);
    }

    @Override
    public Cinema findByIdWithDetails(Long id) {
        return em.createQuery("select c from Cinema c left join Hall h on c.id = h.cinema.id left join Showtime st on h.id = st.hall.id left join Movie m on st.movie.id = m.id where c.id = :id", Cinema.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public boolean exists(String name) {
        try {
            Long count = em.createQuery("select count(*)from Cinema c where c.name = :name", Long.class)
                    .setParameter("name", name)
                    .getSingleResult();
            return count > 0;
        } catch (NoResultException e) {
            System.out.println("e.getMessage() = " + e.getMessage());
        }
        return false;
    }
}
