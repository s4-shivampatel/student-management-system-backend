package com.shivmkp.student_management_system.dto;

import com.shivmkp.student_management_system.enums.Gender;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStudentDto {
        @NotBlank(message = "student id is required")
        @Size(max = 20, message = "Student id must not exceed 50 characters")
        private String studentId;

        @NotBlank(message = "Roll number is required")
        @Size(max = 20, message = "Roll number must not exceed 20 characters")
        private String rollNumber;

        @NotBlank(message = "Name is required")
        @Size(min = 2, max = 100,
                message = "Name must be between 2 and 100 characters")
        private String name;

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        @Size(max = 100, message = "Email must not exceed 100 characters")
        private String email;

        @Size(max = 15, message = "Phone number must not exceed 15 characters")
        @Pattern(
                regexp = "^[0-9]{10,15}$",
                message = "Phone number must contain between 10 and 15 digits"
        )
        private String phone;

        @Past(message = "Date of birth must be in the past")
        private LocalDate dateOfBirth;

        @Pattern(
                regexp = "Male|Female|Other",
                message = "Gender must be Male, Female or Other"
        )
        private Gender gender;

        @Size(max = 500, message = "Address must not exceed 500 characters")
        private String address;

        @NotNull(message = "Department is required")
        private Long departmentId;

        @NotNull(message = "Batch year is required")
        @Min(value = 2000, message = "Invalid batch year")
        private Integer batchYear;

        @Pattern(
                regexp = "ACTIVE|INACTIVE",
                message = "Status must be ACTIVE or INACTIVE"
        )
        private String status;
}

