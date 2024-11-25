package java15.service;

import java15.entities.Hall;
import java15.entities.Showtime;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ShowTimeService {
    void save(Showtime showtime);

    void merge(Showtime showtime);

    List<Showtime> getAll ();

    Showtime findById(Long id);

    void saveShowTime(Long movieId, Hall hall, Showtime showtime);

    void saveShowTime(Long movieId, Long hallId, Showtime showtime);

    void saveShowTime(Long cinemaId,Long movieId, Long hallId,Showtime showtime);

//    void saveShowTime(Long movieId, Long cinemaId, Long hallId,Showtime showtime);
}

