package com.example.ticketapi.dto;

import com.example.ticketapi.model.TicketPriority;
import com.example.ticketapi.model.TicketStatus;
import java.time.LocalDateTime;

public class TicketResponseDTO {
    private Long id;
    private String title;
    private String description;
    private TicketStatus status;
    private TicketPriority priority;
    private LocalDateTime createdAt;

    public TicketResponseDTO(Long id, String title, String description, TicketStatus status, TicketPriority priority, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public TicketStatus getStatus() { return status; }
    public TicketPriority getPriority() { return priority; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
