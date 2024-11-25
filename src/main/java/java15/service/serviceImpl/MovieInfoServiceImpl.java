package java15.service.serviceImpl;

import jakarta.transaction.Transactional;
import java15.entities.Movie;
import java15.entities.MovieInfo;
import java15.repository.MovieInfoRepository;
import java15.repository.MovieRepository;
import java15.service.MovieInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class MovieInfoServiceImpl implements MovieInfoService {

    private final MovieInfoRepository movieInfoRepository;
    private final MovieRepository movieRepository;

    @Override
    public void saveMovieInfo(MovieInfo movieInfo) {
        movieInfoRepository.save(movieInfo);
    }

    @Override
    public void saveMovieWithMovieInfo(Movie movie, MovieInfo movieInfo) {
        movieRepository.save(movie);
        movieInfoRepository.save(movieInfo);
    }
}
