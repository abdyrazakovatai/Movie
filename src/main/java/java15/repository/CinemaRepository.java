package java15.repository;

import jakarta.transaction.Transactional;
import java15.entities.Cinema;
import java15.entities.Movie;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface CinemaRepository {

    void save(Cinema cinema);

    void merge(Cinema cinema);

    void existsByName(List<Cinema> cinemas);

    List<Cinema> getAllCinemas();

    Cinema findByName(String name);

    void saveAll(List<Cinema> cinemas);

    Cinema findByIdWithDetails(Long id);

    Cinema findCinemaById(Long id);

    boolean exists(String name);
}
