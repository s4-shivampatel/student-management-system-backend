package com.shivmkp.student_management_system.service;


import com.shivmkp.student_management_system.dto.CreateTeacherDto;
import com.shivmkp.student_management_system.entity.Teacher;
import com.shivmkp.student_management_system.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;

    public Teacher saveTeacher( CreateTeacherDto createTeacherDto) {
        Teacher teacher = new Teacher();

        teacher.setFirstName(createTeacherDto.getFirstName());
        teacher.setLastName(createTeacherDto.getLastName());
        teacher.setEmail(createTeacherDto.getEmail());
        teacher.setPhone(createTeacherDto.getPhone());
        teacher.setGender(createTeacherDto.getGender());
        teacher.setDateOfBirth(createTeacherDto.getDateOfBirth());
        teacher.setExperience(createTeacherDto.getExperience());
        teacher.setJoiningDate(createTeacherDto.getJoiningDate());
        teacher.setStatus(createTeacherDto.getStatus());

       return teacherRepository.save(teacher);
    }
}
