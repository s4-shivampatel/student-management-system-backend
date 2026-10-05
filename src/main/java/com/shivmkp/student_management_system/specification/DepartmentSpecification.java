package com.shivmkp.student_management_system.specification;

import com.shivmkp.student_management_system.dto.FilterDepartmentDto;
import com.shivmkp.student_management_system.entity.Department;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class DepartmentSpecification {

    public static Specification<Department> filter(
            FilterDepartmentDto filterDepartmentDto
    ) {

        return (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            // Filter by department name
            if (filterDepartmentDto.getName() != null
                    && !filterDepartmentDto.getName().isBlank()) {

                predicates.add(
                        cb.like(
                                cb.lower(root.get("name")),
                                "%" + filterDepartmentDto.getName().toLowerCase() + "%"
                        )
                );
            }

            // Filter by department code
            if (filterDepartmentDto.getCode() != null
                    && !filterDepartmentDto.getCode().isBlank()) {

                predicates.add(
                        cb.like(
                                cb.lower(root.get("code")),
                                "%" + filterDepartmentDto.getCode().toLowerCase() + "%"
                        )
                );
            }

            // Filter by description
//            if (filterDepartmentDto.getDescription() != null
//                    && !filterDepartmentDto.getDescription().isBlank()) {
//
//                predicates.add(
//                        cb.like(
//                                cb.lower(root.get("description")),
//                                "%" + filterDepartmentDto.getDescription().toLowerCase() + "%"
//                        )
//                );
//            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}