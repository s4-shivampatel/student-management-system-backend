package com.shivmkp.student_management_system.specification;

import com.shivmkp.student_management_system.dto.FilterStudentDto;
import com.shivmkp.student_management_system.entity.Student;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;


import java.util.ArrayList;
import java.util.List;

public class StudentSpecification {
    public static Specification<Student> filter(FilterStudentDto filterStudentDto){
        return(root,query,cb)->{

            List<Predicate> predicates=new ArrayList<>();
            if(filterStudentDto.getFirstName()!=null){
                predicates.add(
                        cb.like(
                                cb.lower(root.get("firstName")),
                                "%" + filterStudentDto.getFirstName().toLowerCase() + "%"
                        )
                );
            }
            if(filterStudentDto.getLastName()!=null){
                predicates.add(
                        cb.like(
                                cb.lower(root.get("lastName")),
                                filterStudentDto.getLastName().toLowerCase()
                        )
                );
            }
            if(filterStudentDto.getEmail()!=null){
                predicates.add(
                        cb.equal(root.get("email"), filterStudentDto.getEmail())
                );
            }
            if(filterStudentDto.getPhone()!=null){
                predicates.add(
                        cb.equal(root.get("phone"), filterStudentDto.getPhone())
                );
            }
            if(filterStudentDto.getDateOfBirth()!=null){
                predicates.add(
                        cb.equal(root.get("dateOfBirth"), filterStudentDto.getDateOfBirth())
                );
            }
            if(filterStudentDto.getGender()!=null){
                predicates.add(
                        cb.equal(root.get("gender"), filterStudentDto.getGender())
                );
            }
            if(filterStudentDto.getCourse()!=null){
                predicates.add(
                        cb.equal(root.get("course"), filterStudentDto.getCourse())
                );
            }
            if(filterStudentDto.getYear()!=null){
                predicates.add(
                        cb.equal(root.get("year"), filterStudentDto.getYear())
                );
            }
            return cb.and(predicates);
        };
    }
}
