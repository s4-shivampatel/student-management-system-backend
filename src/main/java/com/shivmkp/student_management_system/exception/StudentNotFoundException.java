package com.shivmkp.student_management_system.exception;

public class StudentNotFoundException extends RuntimeException{
    public StudentNotFoundException() {
        super("Student not found");
    }
    public StudentNotFoundException(Long id) {
        super("Student not found with id:" + id);
    }
}
