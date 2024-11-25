package java15.service.serviceImpl;

import java15.entities.Cinema;
import java15.entities.Movie;
import java15.repository.CinemaRepository;
import java15.service.CinemaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
@RequiredArgsConstructor
public class CinemaServiceImpl implements CinemaService {

    private final CinemaRepository cinemaRepository;

    @Override
    public void save(Cinema cinema) {
        cinemaRepository.save(cinema);
    }

    @Override
    public void merge(Cinema cinema) {
        cinemaRepository.merge(cinema);
    }

    @Override
    public List<Cinema> getAllCinemas() {
        return cinemaRepository.getAllCinemas();
    }

    @Override
    public void exists(List<Cinema> cinemas) {
        cinemaRepository.existsByName(cinemas);
    }

    @Override
    public Cinema getCinemaByName(String name) {
        return null;
    }

    @Override
    public Cinema findByIdWithDetails(Long id) {
        return cinemaRepository.findByIdWithDetails(id);
    }

    @Override
    public void saveAll(List<Cinema> cinemas) {
        cinemaRepository.saveAll(cinemas);
    }

    @Override
    public Cinema findCinemaById(Long id) {
        return cinemaRepository.findCinemaById(id);
    }


}
