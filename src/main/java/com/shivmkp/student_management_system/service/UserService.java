package com.shivmkp.student_management_system.service;


import com.shivmkp.student_management_system.dto.CreateUserDto;
import com.shivmkp.student_management_system.entity.User;
import com.shivmkp.student_management_system.exception.StudentAlreadyExistException;
import com.shivmkp.student_management_system.exception.StudentNotFoundException;
import com.shivmkp.student_management_system.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private  final PasswordEncoder passwordEncoder;


    public User save(CreateUserDto createUserDto) {
        User user =new User();
        if (userRepository.findByStudentId(createUserDto.getStudentId()).isPresent()) {
            throw new StudentAlreadyExistException(createUserDto.getStudentId());
        }
        user.setStudentId(createUserDto.getStudentId());
        user.setPassword(passwordEncoder.encode(createUserDto.getPassword()));
        return userRepository.save(user);
    }
}
