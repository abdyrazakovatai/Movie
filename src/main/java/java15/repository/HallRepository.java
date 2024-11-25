package java15.repository;

import jakarta.transaction.Transactional;
import java15.entities.Cinema;
import java15.entities.Hall;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface HallRepository {

    void save(Hall hall);

    void merge(Hall hall);

    void saveHallForCinema(List<Hall> halls, Cinema cinema);

    void existsByName(List<Hall> halls);

    boolean exists(String name, Long id);

    boolean exists(String name);

    Cinema findByName(String name);

    Hall findByNameHall(String name);

    List<Hall> getAllHalls();

    Hall findHallById(Long id);
}
