package com.shivmkp.student_management_system.dto;

import com.shivmkp.student_management_system.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponseDto {

    private Long id;
    private String studentId;
    private String rollNumber;

    private String name;

    private String email;

    private String phone;

    private LocalDate dateOfBirth;

    private Gender gender;

    private String address;

    private Long departmentId;

    private String departmentName;

    private Integer batchYear;

    private String status;

    private LocalDateTime createdAt;
}

