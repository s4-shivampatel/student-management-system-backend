package com.shivmkp.student_management_system.exception;

public class StudentAlreadyExistException extends RuntimeException{
    public StudentAlreadyExistException(String studentId){
        super("Student Already Exist with Student id "+studentId);
    }
}
