package com.example.ticket_system.mappers;

import com.example.ticket_system.DTO.UserDTO;
import com.example.ticket_system.entities.User;

public class UserDTOMapper {

    public static UserDTO toDto(User user){
        String department = user.getDepartment().getDepartmentName();
        return new  UserDTO(
                user.getUserId(),
                user.getForename(),
                user.getSurname(),
                user.getEmail(),
                department
        );
    }
}
