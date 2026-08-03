package com.shivmkp.student_management_system.dto;

import java.time.LocalDateTime;

public class DepartmentDto {
    private Long id;
    private String departmentCode;
    private String departmentName;
    private String description;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
