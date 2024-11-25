package java15.service;

import java15.entities.Cinema;
import java15.entities.Hall;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface HallService {

    void save(Hall hall);

    public void merge(Hall hall);

    List<Hall> getAllHalls();

    void exists(List<Hall> halls);

    boolean exists(String name);

    void saveForCinema(List<Hall> hall, Cinema cinema);

    Hall findHallById(Long id);

    void saveCinemaWithHalls();

    void mergeCinemaWithHalls(Cinema cinemaWithHalls);

    void assignHallToCinemas(List<Cinema> cinemas, List<Hall> halls);
}
