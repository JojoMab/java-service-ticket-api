package de.jojomab.ticketapi.dto;

import de.jojomab.ticketapi.model.Priority;
import de.jojomab.ticketapi.model.TicketCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TicketRequest {
    @NotBlank
    private String title;
    @NotNull
    private TicketCategory category;
    @NotNull
    private Priority priority;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public TicketCategory getCategory() { return category; }
    public void setCategory(TicketCategory category) { this.category = category; }
    public Priority getPriority() { return priority; }
    public void setPriority(Priority priority) { this.priority = priority; }
}
