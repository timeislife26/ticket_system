package com.example.ticket_system.repositories;

import com.example.ticket_system.entities.Ticket;
import com.example.ticket_system.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    public List<Ticket> findByAssignedUser(User assigned_user);
    @Query("""
    select t from Ticket t
    left join fetch t.assignedUser u
    left join fetch u.department d
    order by t.ticketId desc
""")
    public List<Ticket> findAllTickets();
}
