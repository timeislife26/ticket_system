package com.example.ticket_system.mappers;

import com.example.ticket_system.DTO.DepartmentDTO;
import com.example.ticket_system.entities.Department;

public class DepartmentDTOMapper {

    public static DepartmentDTO toDto(Department department){
        int departmentHead = department.getDepartmentHead().getUserId();
        return new DepartmentDTO(
                department.getDepartmentCode(),
                department.getDepartmentName(),
                departmentHead
        );
    }
}
