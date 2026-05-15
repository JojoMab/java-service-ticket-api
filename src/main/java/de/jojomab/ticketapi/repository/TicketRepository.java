package de.jojomab.ticketapi.repository;

import de.jojomab.ticketapi.model.Ticket;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class TicketRepository {
    private final AtomicLong nextId = new AtomicLong(1);
    private final List<Ticket> tickets = new ArrayList<>();

    public Ticket saveNew(String title, de.jojomab.ticketapi.model.TicketCategory category, de.jojomab.ticketapi.model.Priority priority) {
        Ticket ticket = new Ticket(nextId.getAndIncrement(), title, category, priority, de.jojomab.ticketapi.model.TicketStatus.OPEN);
        tickets.add(ticket);
        return ticket;
    }

    public List<Ticket> findAll() { return List.copyOf(tickets); }
    public Optional<Ticket> findById(long id) { return tickets.stream().filter(ticket -> ticket.getId() == id).findFirst(); }
}
