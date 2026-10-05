package com.shivmkp.student_management_system.dto;

import com.shivmkp.student_management_system.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateUserDto {

    @Size(max = 50)
    private String username;

    private String password;

    private UserRole role;

    private Boolean enabled;

}
