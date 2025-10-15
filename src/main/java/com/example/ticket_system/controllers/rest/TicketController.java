package com.example.ticket_system.controllers.rest;

import com.example.ticket_system.DTO.UpdateAssignedUserDTO;
import com.example.ticket_system.DTO.UpdatePriorityDTO;
import com.example.ticket_system.entities.Ticket;
import com.example.ticket_system.enums.TicketStatus;
import com.example.ticket_system.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@RestController
public class TicketController {
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private TicketRepository ticketRepository;

    @RequestMapping("/")
    public String index(Locale locale){
        return messageSource.getMessage("introduction", null, locale);
    }

    @GetMapping("/tickets")
    public List<Ticket> getAll() {
        return ticketRepository.findAll();
    }

    @GetMapping({"/tickets/{id}"})
    public String PrintTitle(@PathVariable int id, Locale locale){
        Optional<Ticket> ticket = ticketRepository.findById(id);
        if(ticket.isPresent()){
            return ticket.get().getTitle();
        }
        return messageSource.getMessage("noTicket", null, locale);
    }
    @PostMapping({"/create"})
    public ResponseEntity<?> CreateTicket(@RequestBody Ticket ticket, Locale locale){
        if (ticket.getCreated_user_id() <= 0)
            return ResponseEntity.badRequest().body(messageSource.getMessage("noTicketID", null, locale));
        if (ticket.getTitle() == null || ticket.getTitle().isEmpty())
            return ResponseEntity.badRequest().body(messageSource.getMessage("noTicketTitle", null, locale));
        if (ticket.getTitle().length() > 50)
            return ResponseEntity.badRequest().body(messageSource.getMessage("ticketTitleTooLong", null, locale));
        if (ticket.getDescription() == null || ticket.getDescription().isEmpty())
            return ResponseEntity.badRequest().body(messageSource.getMessage("noTicketDescription", null, locale));
        if (ticket.getPriority() <= 0)
            return ResponseEntity.badRequest().body(messageSource.getMessage("badTicketPriority", null, locale));
        if (ticket.getAssigned_user_id() == 0)
            ticket.setStatus(TicketStatus.TO_BE_ASSIGNED);
        else
            ticket.setStatus(TicketStatus.OPEN);
        return ResponseEntity.ok(ticketRepository.save(ticket));
    }
    @PatchMapping({"/update/{id}/priority"})
    public ResponseEntity<?> UpdatePriority(@PathVariable int id, @RequestBody UpdatePriorityDTO updatePriorityDTO, Locale locale){
        Optional<Ticket> ticket = ticketRepository.findById(id);
        if (ticket.isEmpty()){
            return ResponseEntity.badRequest().body(messageSource.getMessage("noTicket", null, locale));
        }
        ticket.get().setPriority(updatePriorityDTO.getPriority());
        return ResponseEntity.ok(ticketRepository.save(ticket.get()));
    }

    @PatchMapping({"/update/{id}/assigned_user"})
    public ResponseEntity<?> UpdateAssignedUSer(@PathVariable int id, @RequestBody UpdateAssignedUserDTO updateAssignedUserDTO, Locale locale){
        Optional<Ticket> ticket = ticketRepository.findById(id);
        if (ticket.isEmpty()){
            return ResponseEntity.badRequest().body(messageSource.getMessage("noTicket", null, locale));
        }
        ticket.get().setAssigned_user_id(updateAssignedUserDTO.getUserId());
        return ResponseEntity.ok(ticketRepository.save(ticket.get()));
    }
}
