package com.shivmkp.student_management_system.service;

import com.shivmkp.student_management_system.dto.CreateUserDto;
import com.shivmkp.student_management_system.dto.UserResponseDto;
import com.shivmkp.student_management_system.entity.Student;
import com.shivmkp.student_management_system.entity.User;
import com.shivmkp.student_management_system.exception.StudentAlreadyExistException;
import com.shivmkp.student_management_system.exception.StudentNotFoundException;
import com.shivmkp.student_management_system.repository.StudentRepository;
import com.shivmkp.student_management_system.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;



    public UserResponseDto save(CreateUserDto createUserDto) {



        User user = new User();
        user.setUsername(createUserDto.getUsername());
        user.setPassword(passwordEncoder.encode(createUserDto.getPassword()));
        user.setRole(createUserDto.getRole());

        User savedUser = userRepository.save(user);

        return UserResponseDto.builder()
                .id(savedUser.getId())
                .username(savedUser.getUsername())
                .role(savedUser.getRole())
                .enabled(savedUser.getEnabled())
                .createdAt(savedUser.getCreatedAt())
                .build();
    }
}