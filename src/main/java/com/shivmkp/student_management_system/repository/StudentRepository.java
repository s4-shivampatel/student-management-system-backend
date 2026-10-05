package com.shivmkp.student_management_system.repository;

import com.shivmkp.student_management_system.entity.Student;
import com.shivmkp.student_management_system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student,Long>, JpaSpecificationExecutor<Student> {
    Optional<User> findByUsername(String username);

}
