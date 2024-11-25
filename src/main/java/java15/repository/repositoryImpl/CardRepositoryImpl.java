package java15.repository.repositoryImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java15.entities.Card;
import java15.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
@RequiredArgsConstructor
public class CardRepositoryImpl implements CardRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public String save(Card card) {
        if (card.getId() == null) {
            em.persist(card);
        }
        else {
            em.merge(card);
        }
        return "Success";
    }


}
