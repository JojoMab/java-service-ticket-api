package de.jojomab.ticketapi.service;

import de.jojomab.ticketapi.model.Priority;
import de.jojomab.ticketapi.model.Ticket;
import de.jojomab.ticketapi.model.TicketCategory;
import org.springframework.stereotype.Service;

@Service
public class SlaRiskService {
    public String calculateRisk(Ticket ticket) {
        if (ticket.getPriority() == Priority.HIGH || ticket.getCategory() == TicketCategory.SECURITY) {
            return "high";
        }
        if (ticket.getPriority() == Priority.MEDIUM || ticket.getCategory() == TicketCategory.DATABASE) {
            return "medium";
        }
        return "normal";
    }
}
