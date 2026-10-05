package com.shivmkp.student_management_system.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequestDto {

    @NotBlank
    private String usename;

    @NotBlank
    private String password;
}
