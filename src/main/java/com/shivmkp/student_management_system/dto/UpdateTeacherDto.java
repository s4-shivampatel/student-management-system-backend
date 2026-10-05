package com.shivmkp.student_management_system.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateTeacherDto {

    @Size(max = 100, message = "Teacher name must not exceed 100 characters")
    private String name;

    @Email(message = "Invalid email format")
    @Size(max = 100, message = "Email must not exceed 100 characters")
    private String email;

    @Size(max = 15, message = "Phone must not exceed 15 characters")
    private String phone;

    @Size(max = 50, message = "Designation must not exceed 50 characters")
    private String designation;

    private Long departmentId;
}