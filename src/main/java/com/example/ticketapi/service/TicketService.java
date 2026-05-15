package com.example.ticketapi.service;

import com.example.ticketapi.dto.TicketRequestDTO;
import com.example.ticketapi.dto.TicketResponseDTO;
import com.example.ticketapi.model.Ticket;
import com.example.ticketapi.model.TicketStatus;
import com.example.ticketapi.repository.TicketRepository;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public TicketResponseDTO createTicket(TicketRequestDTO request) {
        Ticket ticket = new Ticket(request.getTitle(), request.getDescription(), request.getPriority());
        return TicketResponseDTO.fromTicket(ticketRepository.save(ticket));
    }

    public List<TicketResponseDTO> getAllTickets() {
        return ticketRepository.findAll()
                .stream()
                .map(TicketResponseDTO::fromTicket)
                .toList();
    }

    public TicketResponseDTO getTicketById(Long id) {
        return TicketResponseDTO.fromTicket(findTicket(id));
    }

    public TicketResponseDTO updateStatus(Long id, TicketStatus status) {
        Ticket ticket = findTicket(id);
        ticket.setStatus(status);
        return TicketResponseDTO.fromTicket(ticketRepository.save(ticket));
    }

    public void deleteTicket(Long id) {
        Ticket ticket = findTicket(id);
        ticketRepository.delete(ticket);
    }

    private Ticket findTicket(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Ticket not found with id: " + id));
    }
}
