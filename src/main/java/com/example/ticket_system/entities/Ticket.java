package com.example.ticket_system.entities;

import com.example.ticket_system.enums.TicketStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int ticketId;
    private int created_user_id;
    private String title;
    private String description;
    private int priority;
    @Enumerated(EnumType.STRING)
    private TicketStatus status;
    private int assigned_user_id;


}
