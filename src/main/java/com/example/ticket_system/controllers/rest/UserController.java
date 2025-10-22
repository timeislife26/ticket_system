package com.example.ticket_system.controllers.rest;

import com.example.ticket_system.DTO.UserDTO;
import com.example.ticket_system.entities.Department;
import com.example.ticket_system.entities.User;
import com.example.ticket_system.mappers.UserDTOMapper;
import com.example.ticket_system.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;


    @GetMapping("/users")
    public List<UserDTO> getAllUsers(){
        return userRepository.findAll()
                .stream().map(UserDTOMapper::toDto).toList();
    }
    @GetMapping("/users/{id}")
    public UserDTO getUser(@PathVariable int id){
        Optional<User> u = userRepository.findById(id);
        User user = u.get();
        return new UserDTO(
                user.getUserId(),
                user.getForename(),
                user.getSurname(),
                user.getEmail(),
                user.getDepartment().getDepartmentName()
        );
    }

    @GetMapping("/users/head")
    public UserDTO getUserHead(){
        //For testing
        User loggedinUser = userRepository.findById(2).get();

        //Actual function
        Department depatment = loggedinUser.getDepartment();
        User departmentHead = depatment.getDepartmentHead();
        return new UserDTO(
                departmentHead.getUserId(),
                departmentHead.getForename(),
                departmentHead.getSurname(),
                departmentHead.getEmail(),
                departmentHead.getDepartment().getDepartmentName()
        );
    }
}
