package com.shivmkp.student_management_system.service;

import com.shivmkp.student_management_system.entity.User;
import com.shivmkp.student_management_system.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String studentId) throws UsernameNotFoundException {
        User user=userRepository.findByStudentId(studentId).orElseThrow(()-> new UsernameNotFoundException("User not found"));
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getStudentId())
                .password(user.getPassword())
                .build();
    }
}
