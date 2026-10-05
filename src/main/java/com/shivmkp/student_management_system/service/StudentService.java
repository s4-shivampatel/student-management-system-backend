package com.shivmkp.student_management_system.service;

import com.shivmkp.student_management_system.dto.CreateStudentDto;
import com.shivmkp.student_management_system.dto.FilterStudentDto;
import com.shivmkp.student_management_system.dto.StudentResponseDto;
import com.shivmkp.student_management_system.dto.UpdateStudentDto;
import com.shivmkp.student_management_system.entity.Department;
import com.shivmkp.student_management_system.entity.Student;
import com.shivmkp.student_management_system.exception.StudentNotFoundException;
import com.shivmkp.student_management_system.repository.DepartmentRepository;
import com.shivmkp.student_management_system.repository.StudentRepository;
import com.shivmkp.student_management_system.specification.StudentSpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final DepartmentRepository departmentRepository;


    // =========================
    // CREATE STUDENT
    // =========================

    public StudentResponseDto saveStudent(
            CreateStudentDto createStudentDto
    ) {

        Department department = departmentRepository
                .findById(createStudentDto.getDepartmentId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Department not found with id: "
                                        + createStudentDto.getDepartmentId()
                        )
                );

        Student student = new Student();
        student.setStudentId(createStudentDto.getStudentId());
        student.setRollNumber(createStudentDto.getRollNumber());
        student.setName(createStudentDto.getName());
        student.setEmail(createStudentDto.getEmail());
        student.setPhone(createStudentDto.getPhone());
        student.setDateOfBirth(createStudentDto.getDateOfBirth());
        student.setGender(createStudentDto.getGender());
        student.setAddress(createStudentDto.getAddress());
        student.setDepartment(department);
        student.setBatchYear(createStudentDto.getBatchYear());

        // status automatically becomes ACTIVE
        // inside @PrePersist

        Student savedStudent = studentRepository.save(student);

        return convertToResponseDto(savedStudent);
    }


    // =========================
    // GET ALL STUDENTS
    // PAGING + SORTING + FILTER
    // =========================

    public Page<StudentResponseDto> findAllStudent(
            Integer pageNumber,
            Integer pageSize,
            String sortBy,
            String sortDir,
            FilterStudentDto filterStudentDto
    ) {

        Sort sort = sortDir.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Specification<Student> specification =
                StudentSpecification.filter(filterStudentDto);

        Pageable pageable =
                PageRequest.of(pageNumber, pageSize, sort);

        Page<Student> studentPage =
                studentRepository.findAll(specification, pageable);

        return studentPage.map(this::convertToResponseDto);
    }


    // =========================
    // GET STUDENT BY ID
    // =========================

    public StudentResponseDto findAllStudentById(
            Long studentId
    ) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new StudentNotFoundException(studentId)
                );

        return convertToResponseDto(student);
    }


    // =========================
    // PUT UPDATE STUDENT
    // =========================

    public StudentResponseDto putUpdateStudent(
            Long studentId,
            UpdateStudentDto updateStudentDto
    ) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new StudentNotFoundException(studentId)
                );

        Department department = departmentRepository
                .findById(updateStudentDto.getDepartmentId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Department not found with id: "
                                        + updateStudentDto.getDepartmentId()
                        )
                );

        student.setRollNumber(updateStudentDto.getRollNumber());
        student.setName(updateStudentDto.getName());
        student.setEmail(updateStudentDto.getEmail());
        student.setPhone(updateStudentDto.getPhone());
        student.setDateOfBirth(updateStudentDto.getDateOfBirth());
        student.setGender(updateStudentDto.getGender());
        student.setAddress(updateStudentDto.getAddress());
        student.setDepartment(department);
        student.setBatchYear(updateStudentDto.getBatchYear());
        student.setStatus(updateStudentDto.getStatus());

        Student updatedStudent = studentRepository.save(student);

        return convertToResponseDto(updatedStudent);
    }


    // =========================
    // PATCH UPDATE STUDENT
    // =========================

    public StudentResponseDto patchUpdateStudent(
            Long studentId,
            UpdateStudentDto updateStudentDto
    ) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new StudentNotFoundException(studentId)
                );


        if (updateStudentDto.getRollNumber() != null) {
            student.setRollNumber(
                    updateStudentDto.getRollNumber()
            );
        }

        if (updateStudentDto.getName() != null) {
            student.setName(
                    updateStudentDto.getName()
            );
        }

        if (updateStudentDto.getEmail() != null) {
            student.setEmail(
                    updateStudentDto.getEmail()
            );
        }

        if (updateStudentDto.getPhone() != null) {
            student.setPhone(
                    updateStudentDto.getPhone()
            );
        }

        if (updateStudentDto.getDateOfBirth() != null) {
            student.setDateOfBirth(
                    updateStudentDto.getDateOfBirth()
            );
        }

        if (updateStudentDto.getGender() != null) {
            student.setGender(
                    updateStudentDto.getGender()
            );
        }

        if (updateStudentDto.getAddress() != null) {
            student.setAddress(
                    updateStudentDto.getAddress()
            );
        }

        if (updateStudentDto.getDepartmentId() != null) {

            Department department = departmentRepository
                    .findById(updateStudentDto.getDepartmentId())
                    .orElseThrow(() ->
                            new RuntimeException(
                                    "Department not found with id: "
                                            + updateStudentDto.getDepartmentId()
                            )
                    );

            student.setDepartment(department);
        }

        if (updateStudentDto.getBatchYear() != null) {
            student.setBatchYear(
                    updateStudentDto.getBatchYear()
            );
        }

        if (updateStudentDto.getStatus() != null) {
            student.setStatus(
                    updateStudentDto.getStatus()
            );
        }

        Student updatedStudent = studentRepository.save(student);

        return convertToResponseDto(updatedStudent);
    }


    // =========================
    // DELETE STUDENT
    // =========================

    public void deleteStudentById(Long studentId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new StudentNotFoundException(studentId)
                );

        studentRepository.delete(student);
    }


    // =========================
    // ENTITY → RESPONSE DTO
    // =========================

    private StudentResponseDto convertToResponseDto(Student student) {

        return new StudentResponseDto(
                student.getId(),
                student.getStudentId(),
                student.getRollNumber(),
                student.getName(),
                student.getEmail(),
                student.getPhone(),
                student.getDateOfBirth(),
                student.getGender(),
                student.getAddress(),
                student.getDepartment() != null
                        ? student.getDepartment().getId()
                        : null,
                student.getDepartment() != null
                        ? student.getDepartment().getName()
                        : null,
                student.getBatchYear(),
                student.getStatus(),
                student.getCreatedAt()
        );
    }
}