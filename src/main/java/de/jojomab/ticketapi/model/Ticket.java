package de.jojomab.ticketapi.model;

public class Ticket {
    private final long id;
    private final String title;
    private final TicketCategory category;
    private final Priority priority;
    private TicketStatus status;

    public Ticket(long id, String title, TicketCategory category, Priority priority, TicketStatus status) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.priority = priority;
        this.status = status;
    }

    public long getId() { return id; }
    public String getTitle() { return title; }
    public TicketCategory getCategory() { return category; }
    public Priority getPriority() { return priority; }
    public TicketStatus getStatus() { return status; }
    public void setStatus(TicketStatus status) { this.status = status; }
}
