package com.example.ticketapi.controller;

import com.example.ticketapi.model.Priority;
import com.example.ticketapi.model.TicketStatus;
import com.example.ticketapi.repository.TicketRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TicketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TicketRepository ticketRepository;

    @Test
    void postTicketCreatesTicket() throws Exception {
        ticketRepository.deleteAll();

        mockMvc.perform(post("/api/tickets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"VPN login failed\",\"description\":\"User cannot connect.\",\"priority\":\"HIGH\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("VPN login failed"))
                .andExpect(jsonPath("$.status").value(TicketStatus.OPEN.name()))
                .andExpect(jsonPath("$.priority").value(Priority.HIGH.name()));
    }

    @Test
    void getTicketsReturnsList() throws Exception {
        ticketRepository.deleteAll();

        mockMvc.perform(post("/api/tickets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Printer offline\",\"description\":\"Office printer does not respond.\",\"priority\":\"MEDIUM\"}"))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/api/tickets"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Printer offline"));
    }

    @Test
    void patchTicketStatusUpdatesStatus() throws Exception {
        ticketRepository.deleteAll();

        String response = mockMvc.perform(post("/api/tickets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Database access\",\"description\":\"Access request for reporting database.\",\"priority\":\"HIGH\"}"))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Long id = Long.valueOf(response.replaceAll(".*\\\"id\\\":([0-9]+).*", "$1"));

        mockMvc.perform(patch("/api/tickets/" + id + "/status")
                        .param("status", "IN_PROGRESS"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("IN_PROGRESS"));
    }
}
