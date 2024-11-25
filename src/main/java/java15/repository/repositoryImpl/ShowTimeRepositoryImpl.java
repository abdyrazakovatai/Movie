package java15.repository.repositoryImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java15.entities.Showtime;
import java15.repository.ShowTimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class ShowTimeRepositoryImpl implements ShowTimeRepository {
    @PersistenceContext
    private final EntityManager em;

    @Override
    public void save(Showtime showtime) {
        em.persist(showtime);
    }

    public List<Showtime> getAll() {
        return em.createQuery("from Showtime", Showtime.class).getResultList();
    }

    @Override
    public Showtime findById(Long id) {
        return em.createQuery("select st from Showtime st where st.id =: id", Showtime.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public void merge(Showtime showtime) {
        em.merge(showtime);
    }
}
