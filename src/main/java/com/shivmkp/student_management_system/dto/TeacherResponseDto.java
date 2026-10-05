package com.shivmkp.student_management_system.dto;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeacherResponseDto {

    private Long id;

    private String name;

    private String email;

    private String phone;

    private String designation;

    private LocalDateTime createdAt;

    private Long departmentId;

    private String departmentName;
}