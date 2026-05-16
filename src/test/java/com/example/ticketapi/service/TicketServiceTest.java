package com.example.ticketapi.service;

import com.example.ticketapi.dto.TicketRequestDTO;
import com.example.ticketapi.dto.TicketResponseDTO;
import com.example.ticketapi.model.TicketPriority;
import com.example.ticketapi.model.TicketStatus;
import com.example.ticketapi.repository.TicketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@Import(TicketService.class)
class TicketServiceTest {
    @Autowired
    private TicketService ticketService;
    @Autowired
    private TicketRepository ticketRepository;
    private TicketRequestDTO request;

    @BeforeEach
    void setUp() {
        request = new TicketRequestDTO();
        request.setTitle("VPN Login schlägt fehl");
        request.setDescription("Ein Nutzer kann sich nicht per VPN anmelden.");
        request.setPriority(TicketPriority.HIGH);
    }

    @Test
    void createsTicketWithOpenStatus() {
        TicketResponseDTO created = ticketService.createTicket(request);
        assertEquals(TicketStatus.OPEN, created.getStatus());
        assertEquals(TicketPriority.HIGH, created.getPriority());
    }

    @Test
    void returnsAllTickets() {
        ticketService.createTicket(request);
        assertEquals(1, ticketService.getAllTickets().size());
    }

    @Test
    void updatesStatus() {
        TicketResponseDTO created = ticketService.createTicket(request);
        TicketResponseDTO updated = ticketService.updateStatus(created.getId(), TicketStatus.IN_PROGRESS);
        assertEquals(TicketStatus.IN_PROGRESS, updated.getStatus());
    }

    @Test
    void deletesTicket() {
        TicketResponseDTO created = ticketService.createTicket(request);
        ticketService.deleteTicket(created.getId());
        assertTrue(ticketRepository.findAll().isEmpty());
    }

    @Test
    void throwsForMissingTicket() {
        assertThrows(IllegalArgumentException.class, () -> ticketService.getTicketById(999L));
    }
}
