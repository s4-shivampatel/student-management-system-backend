package com.shivmkp.student_management_system.service;


import com.shivmkp.student_management_system.dto.CreateStudentDto;
import com.shivmkp.student_management_system.dto.FilterStudentDto;
import com.shivmkp.student_management_system.dto.UpdateStudentDto;
import com.shivmkp.student_management_system.entity.Student;
import com.shivmkp.student_management_system.exception.StudentNotFoundException;
import com.shivmkp.student_management_system.repository.StudentRepository;
import com.shivmkp.student_management_system.specification.StudentSpecification;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;


    public Student saveStudent(CreateStudentDto createStudentDto) {
        Student student = new Student();
        student.setStudentId(createStudentDto.getStudentId());
        student.setFirstName(createStudentDto.getFirstName());
        student.setLastName(createStudentDto.getLastName());
        student.setEmail(createStudentDto.getEmail());
        student.setPhone(createStudentDto.getPhone());
        student.setDateOfBirth(createStudentDto.getDateOfBirth());
        student.setGender(createStudentDto.getGender());
        student.setAddress(createStudentDto.getAddress());
        student.setCourse(createStudentDto.getCourse());
        student.setYear(createStudentDto.getYear());
        return studentRepository.save(student);
    }

    /*Getting All Students */
//    public List<Student> findAllStudent() {
//        return studentRepository.findAll();
//    }
    /*Getting Students by Paging and Sorting */
    public Page<Student> findAllStudent(Integer pageNumber, Integer pageSize, String sortBy, String sortDir, FilterStudentDto filterStudentDto) {
        Sort sort=sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Specification<Student> specification= StudentSpecification.filter(filterStudentDto);
        Pageable pageable = PageRequest.of(pageNumber,pageSize,sort);
//        Page<Student> page = studentRepository.findAll(pageable);
//        return page.getContent();  ---> for getting only content not other info
        return studentRepository.findAll(specification,pageable);
    }
    public Student findAllStudentById(Long studentId) {
        return studentRepository.findById(studentId).orElseThrow(
                () -> new StudentNotFoundException(studentId));
    }


    public Student putUpdateStudent(Long studentId, UpdateStudentDto updateStudentDto) {
        Student student = new Student();
        student = studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException(studentId));
        student.setFirstName(updateStudentDto.getFirstName());
        student.setLastName(updateStudentDto.getLastName());
        student.setEmail(updateStudentDto.getEmail());
        student.setPhone(updateStudentDto.getPhone());
        student.setDateOfBirth(updateStudentDto.getDateOfBirth());
        student.setGender(updateStudentDto.getGender());
        student.setAddress(updateStudentDto.getAddress());
        student.setCourse(updateStudentDto.getCourse());
        student.setYear(updateStudentDto.getYear());
        return studentRepository.save(student);
    }

    public Student patchUpdateStudent(Long studentId, UpdateStudentDto updateStudentDto) {
        Student student = new Student();
        student = studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException(studentId));
        if (updateStudentDto.getFirstName() != null) {
            student.setFirstName(updateStudentDto.getFirstName());
        }
        if (updateStudentDto.getLastName() != null) {
            student.setLastName(updateStudentDto.getLastName());
        }
        if (updateStudentDto.getEmail() != null) {
            student.setEmail(updateStudentDto.getEmail());
        }
        if (updateStudentDto.getPhone() != null) {
            student.setPhone(updateStudentDto.getPhone());
        }
        if (updateStudentDto.getDateOfBirth() != null) {
            student.setDateOfBirth(updateStudentDto.getDateOfBirth());
        }
        if (updateStudentDto.getGender() != null) {
            student.setGender(updateStudentDto.getGender());
        }
        if (updateStudentDto.getAddress() != null) {
            student.setAddress(updateStudentDto.getAddress());
        }
        if (updateStudentDto.getCourse() != null) {
            student.setCourse(updateStudentDto.getCourse());
        }
        if (updateStudentDto.getYear() != null) {
            student.setYear(updateStudentDto.getYear());
        }

        return studentRepository.save(student);
    }


    public void deleteStudentById(Long studentId) {
        studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException(studentId));
        studentRepository.deleteById(studentId);
    }


}
