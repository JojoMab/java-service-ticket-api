package de.jojomab.ticketapi.service;

import de.jojomab.ticketapi.dto.TicketRequest;
import de.jojomab.ticketapi.model.Priority;
import de.jojomab.ticketapi.model.Ticket;
import de.jojomab.ticketapi.model.TicketStatus;
import de.jojomab.ticketapi.repository.TicketRepository;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class TicketService {
    private final TicketRepository repository;
    private final SlaRiskService slaRiskService;

    public TicketService(TicketRepository repository, SlaRiskService slaRiskService) {
        this.repository = repository;
        this.slaRiskService = slaRiskService;
    }

    public Ticket create(TicketRequest request) {
        return repository.saveNew(request.getTitle(), request.getCategory(), request.getPriority());
    }

    public List<Ticket> findAll() { return repository.findAll(); }

    public Ticket findById(long id) { return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Ticket not found")); }

    public Ticket updateStatus(long id, TicketStatus status) {
        Ticket ticket = findById(id);
        ticket.setStatus(status);
        return ticket;
    }

    public List<Ticket> sortedByPriority() {
        return findAll().stream().sorted(Comparator.comparingInt(this::priorityRank)).toList();
    }

    public Map<String, Object> summary() {
        List<Ticket> tickets = findAll();
        long openTickets = tickets.stream().filter(ticket -> ticket.getStatus() != TicketStatus.CLOSED).count();
        long criticalTickets = tickets.stream().filter(ticket -> slaRiskService.calculateRisk(ticket).equals("high")).count();
        return Map.of("totalTickets", tickets.size(), "openTickets", openTickets, "criticalTickets", criticalTickets);
    }

    private int priorityRank(Ticket ticket) {
        if (ticket.getPriority() == Priority.HIGH) return 0;
        if (ticket.getPriority() == Priority.MEDIUM) return 1;
        return 2;
    }
}
