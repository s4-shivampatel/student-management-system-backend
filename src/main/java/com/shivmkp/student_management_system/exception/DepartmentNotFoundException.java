package com.shivmkp.student_management_system.exception;

public class DepartmentNotFoundException extends RuntimeException{
    public DepartmentNotFoundException(Long departmentId) {
        super("Department not found with id:" + departmentId);
    }
}
