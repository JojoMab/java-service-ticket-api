package com.example.ticketapi.service;

import com.example.ticketapi.dto.TicketRequestDTO;
import com.example.ticketapi.dto.TicketResponseDTO;
import com.example.ticketapi.model.Priority;
import com.example.ticketapi.model.TicketStatus;
import com.example.ticketapi.repository.TicketRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@Import(TicketService.class)
class TicketServiceTest {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TicketService ticketService;

    @Test
    void createTicketStoresTicketWithOpenStatus() {
        TicketRequestDTO request = new TicketRequestDTO();
        request.setTitle("VPN login failed");
        request.setDescription("User cannot connect to company VPN.");
        request.setPriority(Priority.HIGH);

        TicketResponseDTO response = ticketService.createTicket(request);

        assertEquals("VPN login failed", response.getTitle());
        assertEquals(TicketStatus.OPEN, response.getStatus());
        assertEquals(1, ticketRepository.count());
    }

    @Test
    void getAllTicketsReturnsCreatedTickets() {
        ticketService.createTicket(createRequest("Printer offline", Priority.MEDIUM));
        ticketService.createTicket(createRequest("Laptop setup", Priority.LOW));

        assertEquals(2, ticketService.getAllTickets().size());
    }

    @Test
    void updateStatusChangesTicketStatus() {
        TicketResponseDTO created = ticketService.createTicket(createRequest("Database access", Priority.HIGH));

        TicketResponseDTO updated = ticketService.updateStatus(created.getId(), TicketStatus.IN_PROGRESS);

        assertEquals(TicketStatus.IN_PROGRESS, updated.getStatus());
    }

    @Test
    void deleteTicketRemovesTicket() {
        TicketResponseDTO created = ticketService.createTicket(createRequest("Old phone replacement", Priority.LOW));

        ticketService.deleteTicket(created.getId());

        assertEquals(0, ticketRepository.count());
    }

    @Test
    void getUnknownTicketThrowsException() {
        assertThrows(NoSuchElementException.class, () -> ticketService.getTicketById(999L));
    }

    private TicketRequestDTO createRequest(String title, Priority priority) {
        TicketRequestDTO request = new TicketRequestDTO();
        request.setTitle(title);
        request.setDescription("Synthetic applicant project example");
        request.setPriority(priority);
        return request;
    }
}
