package com.shivmkp.student_management_system.service;


import com.shivmkp.student_management_system.dto.CreateUserDto;
import com.shivmkp.student_management_system.entity.User;
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
        user.setUsername(createUserDto.getUsername());
        user.setPassword(passwordEncoder.encode(createUserDto.getPassword()));
        return userRepository.save(user);
    }
}
