package java15.repository.repositoryImpl;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java15.entities.Hall;
import java15.entities.Showtime;
import java15.entities.Ticket;
import java15.repository.TicketRepository;
import java15.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class TicketRepositoryImpl implements TicketRepository {

    private final UserRepository userRepository; // Репозиторий для пользователей

//    private final ShowtTimeRepository showTimeRepository; // Репозиторий для сеансов

    private final EntityManager em;

    @Override
    public void generateTicket(Hall hall) {
//        for (Showtime showTime : hall.getShowTimes()) {
//            int seatCount = hall.getSeatCount();
//            for (int i = 0; i < seatCount; i++) {
//                Ticket ticket = new Ticket();
//                ticket.setSeatNumber(i);
//                ticket.setShowtime(showTime);
//                showTime.getTickets().add(ticket);
            }
//        }
//
//        List<Ticket> tickets = new ArrayList<Ticket>();
//        for (int i = 0; i <=hall.getSeatCount() ; i++) {
//            Ticket ticket = new Ticket();
//            ticket.setSeatNumber(i);
////            ticket.setShowtime(s);
//        }
//    }

    @Override
    public void save(Ticket ticket) {
//        if (ticket.getId() == null) {
//            em.persist(ticket);
//        }else {
            em.merge(ticket);
//        }
    }

    @Override
    public void merge(Ticket ticket) {
        em.merge(ticket);
    }
//
//    @Override
//    public boolean purchaseTickets(Long showtimeId, String selectedSeats) {
//
//            public boolean purchaseTickets(HttpSession session, Long showtimeId, String selectedSeats) {
//                // Получаем текущего пользователя из сессии
//                User currentUser = (User) session.getAttribute("currentUser");
//                if (currentUser == null) {
//                    throw new RuntimeException("Пользователь не найден в сессии");
//                }
//
//                // Получаем информацию о сеансе
//                Showtime showtime = showtimeRepository.findById(showtimeId)
//                        .orElseThrow(() -> new RuntimeException("Сеанс не найден"));
//
//                // Разбираем выбранные места
//                String[] seatNumbers = selectedSeats.split(",");
//                double totalPrice = seatNumbers.length * showtime.getPrice();
//
//                // Проверяем, хватает ли средств у пользователя
//                if (currentUser.getBalance() < totalPrice) {
//                    return false; // Недостаточно средств
//                }
//
//                // Проверяем, что все выбранные места ещё доступны
//                for (String seatNumber : seatNumbers) {
//                    boolean isTaken = ticketRepository.existsByShowtimeAndSeatNumber(showtime, seatNumber);
//                    if (isTaken) {
//                        throw new RuntimeException("Место " + seatNumber + " уже занято");
//                    }
//                }
//
//                // Сохраняем билеты и обновляем баланс пользователя
//                for (String seatNumber : seatNumbers) {
//                    Ticket ticket = new Ticket();
//                    ticket.setShowtime(showtime);
//                    ticket.setSeatNumber(seatNumber);
//                    ticket.setUser(currentUser);
//                    ticketRepository.save(ticket);
//                }
//
//                // Обновляем баланс пользователя
//                currentUser.setBalance(currentUser.getBalance() - totalPrice);
//                userRepository.save(currentUser);
//
//                return true; // Успешная покупка
//            }
        }
