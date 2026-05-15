package com.example.ticketapi.controller;

import com.example.ticketapi.dto.TicketRequestDTO;
import com.example.ticketapi.dto.TicketResponseDTO;
import com.example.ticketapi.model.TicketStatus;
import com.example.ticketapi.service.TicketService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TicketResponseDTO createTicket(@Valid @RequestBody TicketRequestDTO request) {
        return ticketService.createTicket(request);
    }

    @GetMapping
    public List<TicketResponseDTO> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @GetMapping("/{id}")
    public TicketResponseDTO getTicketById(@PathVariable Long id) {
        return ticketService.getTicketById(id);
    }

    @PatchMapping("/{id}/status")
    public TicketResponseDTO updateStatus(@PathVariable Long id, @RequestParam TicketStatus status) {
        return ticketService.updateStatus(id, status);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
    }
}
