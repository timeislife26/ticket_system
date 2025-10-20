package com.example.ticket_system.controllers.rest;

import com.example.ticket_system.DTO.UserDTO;
import com.example.ticket_system.mappers.UserDTOMapper;
import com.example.ticket_system.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;


    @GetMapping("/users")
    public List<UserDTO> getAllUsers(){
        return userRepository.findAll()
                .stream().map(UserDTOMapper::toDto).toList();
    }
}
