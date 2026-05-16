package com.example.ticketapi.controller;

import com.example.ticketapi.dto.TicketRequestDTO;
import com.example.ticketapi.dto.TicketResponseDTO;
import com.example.ticketapi.model.TicketPriority;
import com.example.ticketapi.model.TicketStatus;
import com.example.ticketapi.service.TicketService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TicketController.class)
class TicketControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private TicketService ticketService;

    @Test
    void createsTicketViaRestEndpoint() throws Exception {
        TicketRequestDTO request = new TicketRequestDTO();
        request.setTitle("Datenbankzugriff prüfen");
        request.setDescription("Ein Testticket für die API.");
        request.setPriority(TicketPriority.MEDIUM);
        when(ticketService.createTicket(any())).thenReturn(new TicketResponseDTO(1L, request.getTitle(), request.getDescription(), TicketStatus.OPEN, TicketPriority.MEDIUM, LocalDateTime.now()));

        mockMvc.perform(post("/api/tickets").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void returnsAllTicketsViaRestEndpoint() throws Exception {
        when(ticketService.getAllTickets()).thenReturn(List.of(new TicketResponseDTO(1L, "VPN", "Loginproblem", TicketStatus.OPEN, TicketPriority.HIGH, LocalDateTime.now())));

        mockMvc.perform(get("/api/tickets"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].title").value("VPN"));
    }
}
