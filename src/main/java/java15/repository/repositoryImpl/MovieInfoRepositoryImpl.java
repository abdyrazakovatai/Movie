package java15.repository.repositoryImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java15.entities.Movie;
import java15.entities.MovieInfo;
import java15.repository.MovieInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
@RequiredArgsConstructor
public class MovieInfoRepositoryImpl implements MovieInfoRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(MovieInfo movieInfo) {
        em.persist(movieInfo);
    }

    @Override
    public void saveMovieWithMovieInfo(Movie movie, MovieInfo movieInfo) {
        em.persist(movieInfo);
    }
}
