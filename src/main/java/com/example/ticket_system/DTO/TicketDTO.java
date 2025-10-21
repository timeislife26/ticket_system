package com.example.ticket_system.DTO;

public record TicketDTO(
        int ticketId,
        int createdUser,
        String title,
        String description,
        int priority,
        String status,
        Integer assignedUser
) {
}
