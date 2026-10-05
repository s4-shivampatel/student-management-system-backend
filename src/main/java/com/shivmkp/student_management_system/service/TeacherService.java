package com.shivmkp.student_management_system.service;

import com.shivmkp.student_management_system.dto.CreateTeacherDto;
import com.shivmkp.student_management_system.dto.TeacherResponseDto;
import com.shivmkp.student_management_system.entity.Department;
import com.shivmkp.student_management_system.entity.Teacher;
import com.shivmkp.student_management_system.repository.DepartmentRepository;
import com.shivmkp.student_management_system.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final DepartmentRepository departmentRepository;

    public TeacherResponseDto saveTeacher(CreateTeacherDto createTeacherDto) {

        Department department = departmentRepository
                .findById(createTeacherDto.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));

        Teacher teacher = new Teacher();

        teacher.setName(createTeacherDto.getName());
        teacher.setEmail(createTeacherDto.getEmail());
        teacher.setPhone(createTeacherDto.getPhone());
        teacher.setDesignation(createTeacherDto.getDesignation());
        teacher.setDepartment(department);

        Teacher savedTeacher = teacherRepository.save(teacher);

        return TeacherResponseDto.builder()
                .id(savedTeacher.getId())
                .name(savedTeacher.getName())
                .email(savedTeacher.getEmail())
                .phone(savedTeacher.getPhone())
                .designation(savedTeacher.getDesignation())
                .createdAt(savedTeacher.getCreatedAt())
                .departmentId(savedTeacher.getDepartment().getId())
                .departmentName(savedTeacher.getDepartment().getName())
                .build();
    }
}
