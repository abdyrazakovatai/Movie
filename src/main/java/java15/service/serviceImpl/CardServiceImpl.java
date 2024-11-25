package java15.service.serviceImpl;

import jakarta.transaction.Transactional;
import java15.entities.Card;
import java15.repository.CardRepository;
import java15.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    @Override
    public void save(Card card) {
        cardRepository.save(card);
    }
}
