package com.shivmkp.student_management_system.dto;

import com.shivmkp.student_management_system.enums.UserRole;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilterUserDto {

    private String username;

    private String email;

    private UserRole role;

    private Boolean enabled;

    private Long studentId;

    private Long teacherId;
}