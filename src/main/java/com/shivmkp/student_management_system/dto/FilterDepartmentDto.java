package com.shivmkp.student_management_system.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class FilterDepartmentDto {
    private String departmentCode;
    private Boolean isActive;
}
