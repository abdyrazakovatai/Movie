package java15.service;

import java15.entities.Hall;
import java15.entities.Ticket;

public interface TicketService {
    void generateTicket(Hall hall);

    void save(Ticket ticket);

    void merge(Ticket ticket);
}
