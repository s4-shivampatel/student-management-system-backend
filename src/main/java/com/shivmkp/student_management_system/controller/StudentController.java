package com.shivmkp.student_management_system.controller;

import com.shivmkp.student_management_system.dto.CreateStudentDto;
import com.shivmkp.student_management_system.dto.FilterStudentDto;
import com.shivmkp.student_management_system.dto.StudentResponseDto;
import com.shivmkp.student_management_system.dto.UpdateStudentDto;
import com.shivmkp.student_management_system.service.StudentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;


    // Create Student
    @PostMapping
    public ResponseEntity<StudentResponseDto> createStudent(
            @Valid @RequestBody CreateStudentDto createStudentDto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(studentService.saveStudent(createStudentDto));
    }


    // Get All Students with Pagination, Sorting and Filtering
    @GetMapping
    public ResponseEntity<Page<StudentResponseDto>> getAllStudents(
            @RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber,

            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,

            @RequestParam(value = "sortBy", defaultValue = "id") String sortBy,

            @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir,

            FilterStudentDto filterStudentDto) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(studentService.findAllStudent(
                        pageNumber,
                        pageSize,
                        sortBy,
                        sortDir,
                        filterStudentDto
                ));
    }


    // Get Student By ID
    @GetMapping("/{studentId}")
    public ResponseEntity<StudentResponseDto> getStudentById(
            @PathVariable Long studentId) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(studentService.findAllStudentById(studentId));
    }


    // Full Update Student
    @PutMapping("/{studentId}")
    public ResponseEntity<StudentResponseDto> putUpdateStudent(
            @PathVariable Long studentId,
            @Valid @RequestBody UpdateStudentDto updateStudentDto) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(studentService.putUpdateStudent(
                        studentId,
                        updateStudentDto
                ));
    }


    // Partial Update Student
    @PatchMapping("/{studentId}")
    public ResponseEntity<StudentResponseDto> patchUpdateStudent(
            @PathVariable Long studentId,
            @Valid @RequestBody UpdateStudentDto updateStudentDto) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(studentService.patchUpdateStudent(
                        studentId,
                        updateStudentDto
                ));
    }


    // Delete Student
    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> deleteStudent(
            @PathVariable Long studentId) {

        studentService.deleteStudentById(studentId);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}