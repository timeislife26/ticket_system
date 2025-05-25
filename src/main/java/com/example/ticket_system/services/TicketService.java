package com.example.ticket_system.services;


import com.example.ticket_system.repositories.TicketRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
@AllArgsConstructor
public class TicketService  implements TicketServiceInterface{
    private TicketRepository ticketRepository;
}
