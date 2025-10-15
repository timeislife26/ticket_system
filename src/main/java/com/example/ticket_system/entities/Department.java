package com.example.ticket_system.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_code")
    private int departmentCode;
    private String departmentName;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "department_head_id", referencedColumnName = "user_id", unique = true)
    private User departmentHead;
}
