package com.example.ticket_system.controllers.rest;

import com.example.ticket_system.entities.Department;
import com.example.ticket_system.repositories.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentRepository departmentRepository;

    @GetMapping("/departments")
    public List<Department> getAll(){
        return departmentRepository.findAll();
    }
}
