package com.example.ticket_system.repositories;

import com.example.ticket_system.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
}
