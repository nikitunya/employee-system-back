package com.example.employee.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "employee")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName; // maybe renameit
    private String lastName;
    private String emailId;
}
