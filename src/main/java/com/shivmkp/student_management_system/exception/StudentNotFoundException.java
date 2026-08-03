package com.shivmkp.student_management_system.exception;

public class StudentNotFoundException extends RuntimeException{
    public StudentNotFoundException(Long id) {
        super("Student not found with id:" + id);
    }
}
