package com.shivmkp.student_management_system.controller;


import com.shivmkp.student_management_system.dto.LoginRequestDto;
import com.shivmkp.student_management_system.utils.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    @Autowired
    private final AuthenticationManager authenticationManager;
    @Autowired
    private final JwtUtil jwtUtil;

    @PostMapping("/student/login")
    public ResponseEntity<?> studentLogin(@RequestBody LoginRequestDto loginRequest){
        Authentication authentication=authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getStudentId(),
                        loginRequest.getPassword()
                )
        );
        String token=jwtUtil.generateToken(loginRequest.getStudentId());
        return ResponseEntity.ok(token);
    }

}
