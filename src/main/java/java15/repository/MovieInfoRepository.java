package java15.repository;

import jakarta.transaction.Transactional;
import java15.entities.Movie;
import java15.entities.MovieInfo;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface MovieInfoRepository {
    void save(MovieInfo movieInfo);

    void saveMovieWithMovieInfo(Movie movie, MovieInfo movieInfo);
}
