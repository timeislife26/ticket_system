package com.example.ticket_system.controllers.rest;

import com.example.ticket_system.entities.Ticket;
import com.example.ticket_system.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
