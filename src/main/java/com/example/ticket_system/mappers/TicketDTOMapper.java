package com.example.ticket_system.mappers;

import com.example.ticket_system.DTO.TicketDTO;
import com.example.ticket_system.entities.Ticket;
import org.springframework.stereotype.Component;

@Component
public class TicketDTOMapper{

    public TicketDTOMapper(){}

    public static TicketDTO toDto(Ticket t) {
        Integer uid = (t.getAssignedUser() != null) ? t.getAssignedUser().getUserId() : null;
        return new TicketDTO(
                t.getTicketId(),
                t.getCreatedUserId(),
                t.getTitle(),
                t.getDescription(),
                t.getPriority(),
                t.getStatus().name(),
                uid
        );
    }

}
