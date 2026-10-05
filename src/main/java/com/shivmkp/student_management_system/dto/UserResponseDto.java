package com.shivmkp.student_management_system.dto;


import com.shivmkp.student_management_system.enums.UserRole;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto {

    private Long id;
    private String username;
    private UserRole role;
    private Boolean enabled;
    private LocalDateTime createdAt;

    private Long studentId;
    private Long teacherId;
}