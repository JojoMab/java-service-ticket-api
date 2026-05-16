package com.example.ticketapi.service;

import com.example.ticketapi.dto.TicketRequestDTO;
import com.example.ticketapi.dto.TicketResponseDTO;
import com.example.ticketapi.model.Ticket;
import com.example.ticketapi.model.TicketStatus;
import com.example.ticketapi.repository.TicketRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public TicketResponseDTO createTicket(TicketRequestDTO request) {
        Ticket ticket = new Ticket(request.getTitle(), request.getDescription(), TicketStatus.OPEN, request.getPriority(), LocalDateTime.now());
        return toResponse(ticketRepository.save(ticket));
    }

    public List<TicketResponseDTO> getAllTickets() {
        return ticketRepository.findAll().stream().map(this::toResponse).toList();
    }

    public TicketResponseDTO getTicketById(Long id) {
        return ticketRepository.findById(id).map(this::toResponse).orElseThrow(() -> new IllegalArgumentException("Ticket not found: " + id));
    }

    public TicketResponseDTO updateStatus(Long id, TicketStatus status) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Ticket not found: " + id));
        ticket.setStatus(status);
        return toResponse(ticketRepository.save(ticket));
    }

    public void deleteTicket(Long id) {
        if (!ticketRepository.existsById(id)) {
            throw new IllegalArgumentException("Ticket not found: " + id);
        }
        ticketRepository.deleteById(id);
    }

    private TicketResponseDTO toResponse(Ticket ticket) {
        return new TicketResponseDTO(ticket.getId(), ticket.getTitle(), ticket.getDescription(), ticket.getStatus(), ticket.getPriority(), ticket.getCreatedAt());
    }
}
