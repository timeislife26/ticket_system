package com.example.ticket_system.controllers.rest;

import com.example.ticket_system.DTO.DepartmentDTO;
import com.example.ticket_system.DTO.UserDTO;
import com.example.ticket_system.entities.Department;
import com.example.ticket_system.entities.User;
import com.example.ticket_system.mappers.DepartmentDTOMapper;
import com.example.ticket_system.mappers.UserDTOMapper;
import com.example.ticket_system.repositories.DepartmentRepository;
import com.example.ticket_system.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentRepository departmentRepository;
    private final MessageSource messageSource;
    private final UserRepository userRepository;

    @GetMapping("/department")
    public List<DepartmentDTO> getAll(){
        return departmentRepository.findAll()
                .stream().map(DepartmentDTOMapper::toDto).toList();
    }


    @GetMapping("/department/{id}")
    public String getDepartment(@PathVariable int id, Locale locale){
        Optional<Department> department = departmentRepository.findById(id);
        if (department.isPresent()){
            return department.get().getDepartmentName();
        }
        return messageSource.getMessage("noDepartment", null, locale);
    }

    @GetMapping("/department/{id}/users")
    public List<UserDTO> getDepartmentUsers(@PathVariable int id, Locale locale){
        Optional<Department> department = departmentRepository.findById(id);
            return userRepository.findByDepartment(department.get())
                    .stream().map(UserDTOMapper::toDto).toList();
    }

    @GetMapping("/department/head")
    public List<UserDTO> getAllDepartmentHeads(){
        List<Department> departments = departmentRepository.findAll();
        List<User> users = new ArrayList<>();
        for (Department d : departments){
            users.add(d.getDepartmentHead());
        }
        return users.stream().map(UserDTOMapper::toDto).toList();
    }
    @GetMapping("/department/{id}/head")
    public UserDTO getDepartmentHeadUser(@PathVariable int id){
        Optional<Department> department = departmentRepository.findById(id);
        User user = department.get().getDepartmentHead();
        UserDTO userDTO = new UserDTO(user.getUserId(),
                user.getForename(),
                user.getSurname(),
                user.getEmail(),
                department.get().getDepartmentName()
        );
        return userDTO;
    }
}
