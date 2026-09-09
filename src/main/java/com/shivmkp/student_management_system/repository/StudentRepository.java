package com.shivmkp.student_management_system.repository;

import com.shivmkp.student_management_system.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StudentRepository extends JpaRepository<Student,Long>, JpaSpecificationExecutor<Student> {


}
