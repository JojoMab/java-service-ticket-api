package de.jojomab.ticketapi.controller;

import de.jojomab.ticketapi.dto.TicketRequest;
import de.jojomab.ticketapi.model.Ticket;
import de.jojomab.ticketapi.model.TicketStatus;
import de.jojomab.ticketapi.service.TicketService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.*;

@RestController
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) { this.ticketService = ticketService; }

    @GetMapping("/tickets")
    public List<Ticket> allTickets() { return ticketService.findAll(); }

    @GetMapping("/tickets/{id}")
    public Ticket ticketById(@PathVariable long id) { return ticketService.findById(id); }

    @PostMapping("/tickets")
    public Ticket createTicket(@Valid @RequestBody TicketRequest request) { return ticketService.create(request); }

    @PutMapping("/tickets/{id}/status")
    public Ticket updateStatus(@PathVariable long id, @RequestParam TicketStatus status) { return ticketService.updateStatus(id, status); }

    @GetMapping("/tickets/priority")
    public List<Ticket> byPriority() { return ticketService.sortedByPriority(); }

    @GetMapping("/reports/summary")
    public Map<String, Object> summary() { return ticketService.summary(); }
}
