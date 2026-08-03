package com.shivmkp.student_management_system.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class CreateStudentDto {

    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 30, message = "First name must be between 2 and 30 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 30, message = "Last name must be between 2 and 30 characters")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]{10}$",
            message = "Phone number must contain exactly 10 digits")
    private String phone;

    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    @NotBlank(message = "Gender is required")
    @Pattern(regexp = "Male|Female|Other",
            message = "Gender must be Male, Female or Other")
    private String gender;

    @NotBlank(message = "Address is required")
    @Size(min = 5, max = 200,
            message = "Address must be between 5 and 200 characters")
    private String address;

    @NotBlank(message = "Course is required")
    private String course;

    @NotBlank(message = "Year is required")
    @Pattern(regexp = "1st|2nd|3rd|4th",
            message = "Year must be 1st, 2nd, 3rd or 4th")
    private String year;
}
