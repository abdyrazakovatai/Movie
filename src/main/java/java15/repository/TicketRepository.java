package java15.repository;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import java15.entities.Hall;
import java15.entities.Ticket;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface TicketRepository {
    void generateTicket(Hall hall);

    void save(Ticket ticket);

    public void merge(Ticket ticket);

//    public boolean purchaseTickets(HttpSession session, Long showtimeId, String selectedSeats);

}
