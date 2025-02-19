package java15.repository;

import jakarta.transaction.Transactional;
import java15.entities.Showtime;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface ShowTimeRepository {

    void save (Showtime showtime);

    List<Showtime> getAll ();

    Showtime findById (Long id);

    List<Showtime> findHallById(Long hallId);

    void merge(Showtime showtime);

}
