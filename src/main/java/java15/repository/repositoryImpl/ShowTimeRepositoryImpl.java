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
        em.flush();
        em.clear();
    }

    public List<Showtime> getAll() {
        return em.createQuery("from Showtime", Showtime.class).getResultList();
    }

    @Override
    public Showtime findById(Long id) {
        Showtime showtime = em.createQuery("select st from Showtime st where st.id =: id", Showtime.class)
                .setParameter("id", id)
                .getSingleResult();

        showtime.getTickets().size();
        return showtime;
    }

    @Override
    public List<Showtime> findHallById(Long hallId) {
        return em.createQuery("select st from Showtime st where st.hall.id =: hallId",Showtime.class)
                .setParameter("hallId", hallId)
                .getResultList();
    }

    @Override
    public void merge(Showtime showtime) {
        em.merge(showtime);
    }
}
