package com.shivmkp.student_management_system.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilterTeacherDto {

    private String name;

    private String email;

    private String phone;

    private String designation;

    private Long departmentId;
}
