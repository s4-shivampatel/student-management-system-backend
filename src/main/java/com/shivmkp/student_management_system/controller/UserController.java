package com.shivmkp.student_management_system.controller;

import com.shivmkp.student_management_system.dto.CreateUserDto;
import com.shivmkp.student_management_system.dto.UserResponseDto;
import com.shivmkp.student_management_system.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody CreateUserDto createUserDto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(createUserDto));
    }
}
