package com.example.ticket_system.entities;

import com.example.ticket_system.enums.UserRoles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name= "users")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;
    private String forename;
    private String surname;
    private String email;
    private String password_hash;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "role", length = 32, nullable = false)
    private Set<UserRoles> roles = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "department_code", referencedColumnName = "department_code")
    private Department department;
}
