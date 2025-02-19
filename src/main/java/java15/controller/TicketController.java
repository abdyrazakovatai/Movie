package java15.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import java15.entities.Showtime;
import java15.entities.Ticket;
import java15.entities.User;
import java15.service.ShowTimeService;
import java15.service.TicketService;
import java15.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@Component
@RequestMapping("/tickets")
@RequiredArgsConstructor
public class TicketController {
    private final ShowTimeService showTimeService;
    private final TicketService ticketService;
    private final UserService userService;

    @PostMapping("/purchase")
    @Transactional
    public String purchaseTickets(
            @RequestParam("showtimeId") Long showtimeId,
            @RequestParam("selectedSeats") String selectedSeats,
            HttpSession session,
            Model model) {


        // Получаем текущего пользователя из сессии
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            return "redirect:/users/login"; // Перенаправляем на страницу входа, если пользователь не авторизован
        }

        Showtime showtime = showTimeService.findById(showtimeId);

        if (showtime == null) {
            model.addAttribute("error", "Сеанс не найден.");
            return "buyTicketPage"; // Вернуться на страницу выбора
        }

        // Парсим выбранные места
        List<Integer> seatNumbers = List.of(selectedSeats.split(","))
                .stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        // Подсчёт стоимости
        double totalPrice = seatNumbers.size() * showtime.getPrice();

        if (currentUser.getCard().getSumma() < totalPrice) {
            model.addAttribute("error", "Недостаточно средств.");
            model.addAttribute("remainingBalance", currentUser.getCard().getSumma());
            return "buyTicketPage"; // Вернуться на страницу выбора
        }

        // Покупка: сохраняем билеты и вычитаем деньги
        for (Integer seatNumber : seatNumbers) {
            Ticket ticket = new Ticket();
            ticket.setSeatNumber(seatNumber);
            ticket.setShowtime(showtime);
            ticket.setUser(currentUser);
            currentUser.getTickets().add(ticket);
            ticketService.save(ticket);
        }

//        showtime = showTimeService.findById(showtimeId);
        List<Ticket> allTickets = showtime.getTickets();

        System.out.println("adilet = ");

        System.out.println("All tickets for ShowTime: ");
        for (Ticket ticket : allTickets) {
            System.out.println("Mesto " + ticket.getSeatNumber() + "user " + (ticket.getUser() != null ? ticket.getUser().getName() : "no"));
        }

        currentUser.getCard().setSumma(currentUser.getCard().getSumma() - totalPrice);
        userService.save(currentUser);

        // Обновляем список занятых мест
//        List<Integer> takenSeats = showtime.getTickets().stream()
//                .filter(ticket -> ticket.getUser() != null)
//                .map(Ticket::getSeatNumber)
//                .collect(Collectors.toList());

        // Обновляем список занятых мест
        List<Integer> takenSeats = allTickets.stream()
                .filter(ticket -> ticket.getUser() != null)
                .map(Ticket::getSeatNumber)
                .collect(Collectors.toList());
        System.out.println("Занятые места: " + takenSeats);



        System.out.println("Купленные места: " + String.join(", ", seatNumbers.stream().map(String::valueOf).collect(Collectors.toList())));
        System.out.println("Оставшийся баланс: " + currentUser.getCard().getSumma());

        model.addAttribute("message","Покупка прошла успешно!");
        model.addAttribute("seatNumbers", String.join(", ", seatNumbers.stream().map(String::valueOf).collect(Collectors.toList())));
        model.addAttribute("remainingBalance", currentUser.getCard().getSumma());
        model.addAttribute("takenSeats", takenSeats); // Занятые места
        System.out.println("takenSeats = " + takenSeats);
        model.addAttribute("showtime", showtime);
        return "buyTicketPage"; // Страница подтверждения
    }

    @GetMapping("/buy")
    public String showTicketPage(@RequestParam(value = "showtimeId", required = false) Long showtimeId, Model model) {
        if (showtimeId != null) {
            model.addAttribute("showtime", showTimeService.findById(showtimeId));
        }
        return "purchaseConfirmationPage";
    }
}

