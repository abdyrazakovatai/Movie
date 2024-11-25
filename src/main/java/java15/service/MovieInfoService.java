package java15.service;

import java15.entities.Movie;
import java15.entities.MovieInfo;
import org.springframework.stereotype.Service;

@Service
//@RequiredArgsConstructor
public interface MovieInfoService {
    void saveMovieInfo(MovieInfo movieInfo);
    void saveMovieWithMovieInfo(Movie movie, MovieInfo movieInfo);
}
