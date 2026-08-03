package com.shivmkp.student_management_system.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@AllArgsConstructor
@Getter
@Setter
public class CreateDepartmentDto {

    @NotBlank(message = "Department code is Required")
    private String departmentCode;
    @NotBlank(message = "Department name is required")
    private String departmentName;
    @NotBlank(message = "Description is required")
    private String description;
    @NotNull(message = "Status is required")
    private Boolean isActive;
}
