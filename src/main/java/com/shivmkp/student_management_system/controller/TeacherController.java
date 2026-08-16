package com.shivmkp.student_management_system.controller;


import com.shivmkp.student_management_system.dto.CreateTeacherDto;
import com.shivmkp.student_management_system.entity.Teacher;
import com.shivmkp.student_management_system.service.TeacherService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/teachers")
public class TeacherController {
    private final TeacherService teacherService;

    @PostMapping
    public ResponseEntity<Teacher> createTeacher(@Valid @RequestBody CreateTeacherDto createTeacherDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(teacherService.saveTeacher(createTeacherDto));
    }
    

}
