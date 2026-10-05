package com.shivmkp.student_management_system.specification;

import com.shivmkp.student_management_system.dto.FilterStudentDto;
import com.shivmkp.student_management_system.entity.Student;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class StudentSpecification {

    public static Specification<Student> filter(FilterStudentDto filterStudentDto) {

        return (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            // Roll Number
            if (filterStudentDto.getRollNumber() != null &&
                    !filterStudentDto.getRollNumber().isBlank()) {

                predicates.add(
                        cb.like(
                                cb.lower(root.get("rollNumber")),
                                "%" + filterStudentDto.getRollNumber().toLowerCase() + "%"
                        )
                );
            }

            // Name
            if (filterStudentDto.getName() != null &&
                    !filterStudentDto.getName().isBlank()) {

                predicates.add(
                        cb.like(
                                cb.lower(root.get("name")),
                                "%" + filterStudentDto.getName().toLowerCase() + "%"
                        )
                );
            }

            // Email
            if (filterStudentDto.getEmail() != null &&
                    !filterStudentDto.getEmail().isBlank()) {

                predicates.add(
                        cb.equal(
                                cb.lower(root.get("email")),
                                filterStudentDto.getEmail().toLowerCase()
                        )
                );
            }

            // Phone
            if (filterStudentDto.getPhone() != null &&
                    !filterStudentDto.getPhone().isBlank()) {

                predicates.add(
                        cb.equal(
                                root.get("phone"),
                                filterStudentDto.getPhone()
                        )
                );
            }

            // Date of Birth
            if (filterStudentDto.getDateOfBirth() != null) {

                predicates.add(
                        cb.equal(
                                root.get("dateOfBirth"),
                                filterStudentDto.getDateOfBirth()
                        )
                );
            }

            // Gender
            if (filterStudentDto.getGender() != null) {

                predicates.add(
                        cb.equal(
                                root.get("gender"),
                                filterStudentDto.getGender()
                        )
                );
            }

            // Address
            if (filterStudentDto.getAddress() != null &&
                    !filterStudentDto.getAddress().isBlank()) {

                predicates.add(
                        cb.like(
                                cb.lower(root.get("address")),
                                "%" + filterStudentDto.getAddress().toLowerCase() + "%"
                        )
                );
            }

            // Department
            if (filterStudentDto.getDepartmentId() != null) {

                predicates.add(
                        cb.equal(
                                root.get("department").get("id"),
                                filterStudentDto.getDepartmentId()
                        )
                );
            }

            // Batch Year
            if (filterStudentDto.getBatchYear() != null) {

                predicates.add(
                        cb.equal(
                                root.get("batchYear"),
                                filterStudentDto.getBatchYear()
                        )
                );
            }

            // Status
            if (filterStudentDto.getStatus() != null &&
                    !filterStudentDto.getStatus().isBlank()) {

                predicates.add(
                        cb.equal(
                                cb.upper(root.get("status")),
                                filterStudentDto.getStatus().toUpperCase()
                        )
                );
            }

            return cb.and(predicates);
        };
    }
}