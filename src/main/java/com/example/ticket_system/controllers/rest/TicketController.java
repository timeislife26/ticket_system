package com.example.ticket_system.controllers.rest;

import com.example.ticket_system.DTO.TicketDTO;
import com.example.ticket_system.DTO.UpdateAssignedUserDTO;
import com.example.ticket_system.DTO.UpdatePriorityDTO;
import com.example.ticket_system.entities.Ticket;
import com.example.ticket_system.entities.User;
import com.example.ticket_system.enums.TicketStatus;
import com.example.ticket_system.mappers.TicketDTOMapper;
import com.example.ticket_system.repositories.TicketRepository;
import com.example.ticket_system.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class TicketController {



    private final MessageSource messageSource;
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;





    @RequestMapping("/")
    public String index(Locale locale){
        return messageSource.getMessage("introduction", null, locale);
    }

    @GetMapping("/tickets")
    public List<TicketDTO> getAll() {
        return ticketRepository.findAllTickets()
                .stream().map(TicketDTOMapper::toDto).toList();
    }

    @GetMapping({"/tickets/{id}"})
    public String GetTicketByID(@PathVariable int id, Locale locale){
        Optional<Ticket> ticket = ticketRepository.findById(id);
        if(ticket.isPresent()){
            System.out.println(ticket.get().getAssignedUser());
            return ticket.get().getTitle();
        }
        return messageSource.getMessage("noTicket", null, locale);
    }

    @GetMapping("/myTickets")
    public List<TicketDTO> getMyTickets(){
        //For testing
        Optional<User> user = userRepository.findById(2);
        return ticketRepository.findByAssignedUser(user.get())
                .stream().map(TicketDTOMapper::toDto).toList();
    }


    @PostMapping({"/create"})
    public ResponseEntity<?> CreateTicket(@RequestBody Ticket ticket, Locale locale){
        if (ticket.getCreatedUserId() <= 0)
            return ResponseEntity.badRequest().body(messageSource.getMessage("noTicketID", null, locale));
        if (ticket.getTitle() == null || ticket.getTitle().isEmpty())
            return ResponseEntity.badRequest().body(messageSource.getMessage("noTicketTitle", null, locale));
        if (ticket.getTitle().length() > 50)
            return ResponseEntity.badRequest().body(messageSource.getMessage("ticketTitleTooLong", null, locale));
        if (ticket.getDescription() == null || ticket.getDescription().isEmpty())
            return ResponseEntity.badRequest().body(messageSource.getMessage("noTicketDescription", null, locale));
        if (ticket.getPriority() <= 0)
            return ResponseEntity.badRequest().body(messageSource.getMessage("badTicketPriority", null, locale));
        if (ticket.getAssignedUser().getUserId() == 0)
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

    /*@PatchMapping({"/update/{id}/assigned_user"})
    public ResponseEntity<?> UpdateAssignedUser(@PathVariable int id, @RequestBody UpdateAssignedUserDTO updateAssignedUserDTO, Locale locale){
        Optional<Ticket> ticket = ticketRepository.findById(id);
        if (ticket.isEmpty()){
            return ResponseEntity.badRequest().body(messageSource.getMessage("noTicket", null, locale));
        }
        Optional<User> aUser = userRepository.findById(updateAssignedUserDTO.getUserId());
        if (aUser.isEmpty())
            ticket.get().setAssignedUser(null);
        else
            ticket.get().setAssignedUser(aUser.get());
        return ResponseEntity.ok(ticketRepository.save(ticket.get()));
    }*/


}
