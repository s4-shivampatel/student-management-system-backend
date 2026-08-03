package com.shivmkp.student_management_system.repository;

import com.shivmkp.student_management_system.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DepartmentRepository extends JpaRepository<Department,Long>, JpaSpecificationExecutor<Department> {
}
