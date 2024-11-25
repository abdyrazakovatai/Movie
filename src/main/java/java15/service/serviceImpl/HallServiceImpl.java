package java15.service.serviceImpl;

import java15.entities.Cinema;
import java15.entities.Hall;
import java15.repository.CinemaRepository;
import java15.repository.HallRepository;
import java15.service.HallService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HallServiceImpl implements HallService {

    private final HallRepository hallRepository;
    private final CinemaRepository cinemaRepository;

    @Override
    public void save(Hall hall) {
        hallRepository.save(hall);
    }

    @Override
    public void merge(Hall hall) {
        hallRepository.merge(hall);
    }

    @Override
    public List<Hall> getAllHalls() {
        return hallRepository.getAllHalls();
    }

    @Override
    public void exists(List<Hall> halls) {
        hallRepository.existsByName(halls);
    }

    @Override
    public boolean exists(String name) {
        return hallRepository.exists(name);
    }

    @Override
    public void saveForCinema(List<Hall> halls, Cinema cinema) {
        for (Hall hall : halls) {
            boolean exists = hallRepository.exists(hall.getName(), cinema.getId());
            if (!exists) {
                hall.setCinema(cinema);
                hallRepository.save(hall);
                System.out.println("Hall " + hall.getName() + " has been saved" + cinema.getName());
            } else {
//                System.out.println("Hall " + hall.getName() + " already exists" + cinema.getName());
            }
        }
    }

    @Override
    public Hall findHallById(Long id) {
       return hallRepository.findHallById(id);
    }


    @Override
    public void assignHallToCinemas(List<Cinema> cinemas, List<Hall> halls) {
        System.out.println("assignHallToCinemas");
        if (cinemas.isEmpty() || halls.isEmpty()) {
            throw new IllegalArgumentException("Cinema and Hall cannot be empty");
        }

        if (cinemas.isEmpty() || halls.isEmpty()) {
            throw new IllegalArgumentException("Cinema and Hall cannot be empty");
        }
        for (Cinema cinema : cinemas) {
            Cinema existing = cinemaRepository.findByName(cinema.getName());
            if (existing == null) {
                cinemaRepository.save(cinema);
            } else {
                cinema = existing;
            }
            for (Hall hall : halls) {
                Hall existig = hallRepository.findByNameHall(hall.getName());
                if (existig == null) {
                    hall.setCinema(cinema);
                    hallRepository.save(hall);
                } else {
                    hall = existig;
                    hall.setCinema(cinema);
                }
                if (!cinema.getHalls().contains(hall)) {
                    cinema.getHalls().add(hall);
                }
                cinemaRepository.save(cinema);
            }
        }
    }

    @Override
    public void saveCinemaWithHalls() {

    }

    @Override
    public void mergeCinemaWithHalls(Cinema cinemaWithHalls) {

    }
}

