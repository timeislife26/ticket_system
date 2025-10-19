package com.example.ticket_system.DTO;

public record TicketDTO(
        int ticketId,
        int createdUserId,
        String title,
        String description,
        int priority,
        String status,
        int assignedUserId
) {
}
