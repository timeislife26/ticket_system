package com.example.ticket_system.DTO;

public record UserDTO(
        int userID,
        String forename,
        String surname,
        String email,
        String department
) {
}
