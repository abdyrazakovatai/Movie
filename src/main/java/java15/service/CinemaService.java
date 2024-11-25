package java15.service;

import java15.entities.Cinema;
import java15.entities.Movie;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CinemaService {

    void save(Cinema cinema);

    public void merge(Cinema cinema);

    List<Cinema> getAllCinemas();

    void exists(List<Cinema> cinemas);

    Cinema getCinemaByName(String name);

    Cinema findByIdWithDetails(Long id);

    void saveAll(List<Cinema> cinemas);

    Cinema findCinemaById(Long id);
}
