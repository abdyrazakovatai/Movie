package java15.repository;

import jakarta.transaction.Transactional;
import java15.entities.Card;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface CardRepository {

    String save(Card card);

}
