package com.shivmkp.student_management_system.dto;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DepartmentResponseDto {

    private Long id;

    private String name;

    private String code;

    private String description;

    private LocalDateTime createdAt;
}