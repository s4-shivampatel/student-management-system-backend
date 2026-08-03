package com.shivmkp.student_management_system.specification;

import com.shivmkp.student_management_system.dto.FilterDepartmentDto;
import com.shivmkp.student_management_system.entity.Department;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DepartmentSpecification {
    public static Specification<Department> filter(FilterDepartmentDto filterDepartmentDto){
        return (root,query,cb)->{
            List<Predicate> predicates=new ArrayList<>();
            if(filterDepartmentDto.getDepartmentCode()!=null){
                predicates.add(
                        cb.like(
                                cb.lower(root.get("departmentCode")),
                                "%"+filterDepartmentDto.getDepartmentCode().toLowerCase()+"%"
                        )
                );
            }
            if(filterDepartmentDto.getIsActive()!=null){
                predicates.add(
                        cb.equal(
                                (root.get("isActive")),
                                filterDepartmentDto.getIsActive()
                        )
                );
            }
            return cb.and(predicates.toArray(new Predicate[0]));

        };
    }
}
