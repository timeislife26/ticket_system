package com.example.ticket_system.controllers.rest;

import com.example.ticket_system.entities.Ticket;
import com.example.ticket_system.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class WebController {
    @Autowired
    private TicketRepository ticketRepository;

    @RequestMapping("/")
    public String index(){
        return "Welcome to the Ticket System";
    }

    @GetMapping("/tickets")
    public List<Ticket> getAll() {
        return ticketRepository.findAll();
    }

    @GetMapping({"/tickets/{id}"})
    public String PrintTitle(@PathVariable int id){
        Optional<Ticket> ticket = ticketRepository.findById(id);
        if(ticket.isPresent()){
            return ticket.get().getTitle();
        }
        return "Ticket not found";
    }
    @PostMapping({"/create"})
    public ResponseEntity<?> CreateTicket(@RequestBody Ticket ticket){
        if (ticket.getCreated_user_id() <= 0)
            return ResponseEntity.badRequest().body("Missing user ID for ticket");
        if (ticket.getTitle() == null || ticket.getTitle().isEmpty())
            return ResponseEntity.badRequest().body("Ticket title is required");
        if (ticket.getTitle().length() > 50)
            return ResponseEntity.badRequest().body("Ticket title is too long");
        if (ticket.getDescription() == null || ticket.getDescription().isEmpty())
            return ResponseEntity.badRequest().body("Please enter the ticket description");
        if (ticket.getPriority() <= 0)
            return ResponseEntity.badRequest().body("Please enter a valid priority");
        if (ticket.getAssigned_user_id() == 0)
            ticket.setStatus("Unassigned");
        else
            ticket.setStatus("Assigned");
        return ResponseEntity.ok(ticketRepository.save(ticket));
    }
}
