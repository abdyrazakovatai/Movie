package java15.repository.repositoryImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java15.entities.Movie;
import java15.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
@RequiredArgsConstructor
public class MovieRepositoryImpl implements MovieRepository {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public List<Movie> getAll() {
        return em.createQuery("from Movie", Movie.class).getResultList();
    }

    @Override
    public void merge(Movie movie) {
        em.merge(movie);
    }



    @Override
    public void save(Movie movie) {
        em.persist(movie);
    }

    @Override
    public String update(String movieName, Movie movie) {
        return "";
    }

    @Override
    public Movie findById(Long id) {
        return em.createQuery("select m from Movie m where m.id = :id", Movie.class).setParameter("id", id).getSingleResult();

    }

    @Override
    public Movie findByIdMovie(Long id) {
         Movie movie = em.createQuery(
                "select distinct m from Movie m" +
                " left join fetch m.cinemas c" +
                " left join fetch m.showTimes st " +
                " left join fetch st.hall h " +
                "where m.id = :id ", Movie.class)
                 .setParameter("id", id).getSingleResult();
//         movie.getShowTimes().forEach(showtime ->
//         {
//             if (showtime.getHall() != null) {
//                 showtime.getHall().getName();
//             }
//         });
         return movie;

    }

    @Override
    public Movie findMovieByName(String movieName) {
        return em.createQuery("from Movie where movieName = :movieName", Movie.class).getSingleResult();
    }

    @Override
    public void delete(String movieName) {

    }
}
