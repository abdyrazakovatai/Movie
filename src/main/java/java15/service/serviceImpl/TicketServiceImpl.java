package java15.service.serviceImpl;

import java15.entities.Hall;
import java15.entities.Ticket;
import java15.repository.TicketRepository;
import java15.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    @Override
    public void generateTicket(Hall hall) {
        ticketRepository.generateTicket(hall);
    }

    @Override
    public void save(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    @Override
    public void merge(Ticket ticket) {
        ticketRepository.merge(ticket);
    }


}
