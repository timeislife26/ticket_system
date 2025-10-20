package com.example.ticket_system.repositories;

import com.example.ticket_system.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
