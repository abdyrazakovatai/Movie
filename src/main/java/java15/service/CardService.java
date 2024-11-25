package java15.service;

import jakarta.transaction.Transactional;
import java15.entities.Card;
import org.springframework.stereotype.Service;

@Service
@Transactional
public interface CardService {

    void save(Card card);
}
