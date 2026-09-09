package com.shivmkp.student_management_system.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long userId;

    @Column(unique = true,nullable = false )
    private String studentId;

    private String password;
    @ElementCollection
    private List<String> roles;

}
