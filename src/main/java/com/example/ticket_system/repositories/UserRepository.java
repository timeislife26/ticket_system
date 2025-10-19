package com.example.ticket_system.repositories;

import com.example.ticket_system.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
