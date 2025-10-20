package com.example.ticket_system.controllers.rest;

import com.example.ticket_system.DTO.DepartmentDTO;
import com.example.ticket_system.mappers.DepartmentDTOMapper;
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
    public List<DepartmentDTO> getAll(){
        return departmentRepository.findAll()
                .stream().map(DepartmentDTOMapper::toDto).toList();
    }
}
