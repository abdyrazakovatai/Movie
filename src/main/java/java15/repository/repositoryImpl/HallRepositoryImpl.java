package java15.repository.repositoryImpl;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java15.entities.Cinema;
import java15.entities.Hall;
import java15.repository.HallRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class HallRepositoryImpl implements HallRepository {
    @PersistenceContext
    private final EntityManager em;

    @Override
    public void save(Hall hall) {
        em.persist(hall);
    }

    @Override
    public void merge(Hall hall) {
        em.merge(hall);
    }

    @Override
    public void saveHallForCinema(List<Hall> halls, Cinema cinema) {
        for (Hall hall : halls) {
            if (!exists(hall.getName(), cinema.getId())) {
                hall.setCinema(cinema);
                save(hall);
                System.out.println("hall" + hall.getName() + "has been added to cinema");
            } else {
                System.out.println("hall" + hall.getName() + "already exists  cinema");
            }
        }
    }

    @Override
    public List<Hall> getAllHalls() {
        return em.createQuery("select h from Hall h", Hall.class).getResultList();
    }

    @Override
    public Hall findHallById(Long id) {
        return em.find(Hall.class, id);
    }

    @Override
    public boolean exists(String name, Long cinemaId) {

        Long count = em.createQuery("select count (h) > 0 from Hall h where h.name=: name and h.cinema.id=:cinemaId", Long.class)
                .setParameter("name", name)
                .setParameter("cinemaId", cinemaId)
                .getSingleResult();
        return count > 0;
    }

    @Override
    public boolean exists(String name) {
        try {
            Long count = em.createQuery("select count(*)from Hall h where h.name = :name", Long.class)
                    .setParameter("name", name)
                    .getSingleResult();
            return count > 0;
        } catch (NoResultException e) {
            System.out.println("e.getMessage() = " + e.getMessage());
        }
        return false;
//    }
    }

    @Override
    public Cinema findByName(String name) {
        try {
            System.out.println("name = " + name);
            return em.createQuery("select c from Cinema c where c.name=:name", Cinema.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Hall findByNameHall(String name) {
        try {
            return em.createQuery("select h from Hall h where h.name=:name", Hall.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void existsByName(List<Hall> halls) {
//        for (Hall hall : halls) {
//            boolean exists = exists(hall.getName(), );
//
//            if (!exists) {
//                saveForCinema(hall);
//                System.out.println("Cinema '" + hall.getName() + "' has been added");
//            } else {
//                System.out.println("Cinema '" + hall.getName() + "' already exists");
//            }
    }

    @PostConstruct
    public void init() {
//        try {
        System.out.println("HallServiceImpl init");

//        List<Cinema> cinemas = Arrays.asList(
//                new Cinema("Cosmo Park", "https://scontent.ffru2-1.fna.fbcdn.net/v/t1.6435-9/89922332_2688504551386165_8891108522113105920_n.jpg?_nc_cat=106&ccb=1-7&_nc_sid=0b6b33&_nc_ohc=gll3PFDY2_QQ7kNvgEzPP9i&_nc_zt=23&_nc_ht=scontent.ffru2-1.fna&_nc_gid=AV_mB-roVZGQTjEi4kZ07Tp&oh=00_AYCRr84bobu2aqgBn9TZFSyRnRbp3Ke6tTgE_avot_81yA&oe=675FE747"),
//                new Cinema("Bishkek Park", "https://scontent.ffru2-1.fna.fbcdn.net/v/t39.30808-6/464034225_3060134200815662_4788163128619447872_n.jpg?_nc_cat=102&ccb=1-7&_nc_sid=cc71e4&_nc_ohc=RkZyp8VlVS0Q7kNvgHeZa21&_nc_zt=23&_nc_ht=scontent.ffru2-1.fna&_nc_gid=AKlX-IvFFKNc2zmcU-VO9eR&oh=00_AYAGy7JTHNC8eW7fe2zS1FFS0VSUcS24qmdWqCNJlTK0sg&oe=673E5464"),
//                new Cinema("Ala Too", "https://alatoocinema.com/design/export_logo.png"),
//                new Cinema("Russia", "https://share.cdn.viber.com/pg_download?id=0-04-06-cbf5967a085978e78cc57c5b6f820575222f798f20e9dc1670608888a08bab69&filetype=jpg&type=icon"),
//                new Cinema("Asia Mall", "https://play-lh.googleusercontent.com/h0VuxBjEmWjHZLZELWpOnP3sLN0dXqDF0FncqFAAVTAMYFQsmblSt59THRCoyxSSRgA=w1024-h500"),
//                new Cinema("Manas", "https://manascinema.com/design/export_logo.jpg"),
//                new Cinema("Dordoi Plaza", "https://cinematica.kg/uploads/cinemas/413acb05-2ed6-4f9c-8bac-f2185ac78cc3.png"),
//                new Cinema("Alamedin Grand", "https://cinematica.kg/uploads/cinemas/193e6dbd-f552-452e-827c-e25ccdba0048.PNG"),
//                new Cinema("Dordoi Motors", "https://cinematica.kg/uploads/cinemas/3e6c59c6-303e-4966-b9f1-6dab81bfba09.jpeg")
//        );
//        List<Hall> halls = Arrays.asList(
//                new Hall("Синий зал", 50),
//                new Hall("Красный зал", 50),
//                new Hall("Серый зал", 50),
//                new Hall("Большой зал", 50),
//                new Hall("Зеленый зал", 50),
//                new Hall("Imax", 50),
//                new Hall("Dolby Atmos", 50)
//        );
//        for (Hall hall : halls) {
//            System.out.println("hall = " + hall);
//            if (!exists(hall.getName())) {
//                save(hall);
//            }
//        }
//        for (Cinema cinema : cinemas) {
//            System.out.println("cinema = " + cinema);
//            if ("Manas".equals(cinema.getName())){
//                cinema = em.merge(cinema);
//
//                for (Hall hall : halls) {
//                    System.out.println("hall = " + hall);
//                    hall.setCinema(cinema);
//                    em.merge(hall);
//                }
//            }else {
//                em.merge(cinema);
//            }
//        }
    }
}

