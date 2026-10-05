package com.shivmkp.student_management_system.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FilterDepartmentDto {

    private String name;
    private String code;
    private String description;
}