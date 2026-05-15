package de.jojomab.ticketapi;

import de.jojomab.ticketapi.dto.TicketRequest;
import de.jojomab.ticketapi.model.Priority;
import de.jojomab.ticketapi.model.TicketCategory;
import de.jojomab.ticketapi.repository.TicketRepository;
import de.jojomab.ticketapi.service.SlaRiskService;
import de.jojomab.ticketapi.service.TicketService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TicketServiceTest {
    @Test
    void createsTicketAndReportsCriticalTickets() {
        TicketService service = new TicketService(new TicketRepository(), new SlaRiskService());
        TicketRequest request = new TicketRequest();
        request.setTitle("VPN login failed");
        request.setCategory(TicketCategory.SECURITY);
        request.setPriority(Priority.HIGH);

        service.create(request);

        assertEquals(1, service.summary().get("criticalTickets"));
    }
}
