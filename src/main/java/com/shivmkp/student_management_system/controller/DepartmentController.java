package com.shivmkp.student_management_system.controller;

import com.shivmkp.student_management_system.dto.CreateDepartmentDto;
import com.shivmkp.student_management_system.dto.DepartmentResponseDto;
import com.shivmkp.student_management_system.dto.FilterDepartmentDto;
import com.shivmkp.student_management_system.dto.UpdateDepartmentDto;
import com.shivmkp.student_management_system.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/departments")
@AllArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    // CREATE
    @PostMapping
    public ResponseEntity<DepartmentResponseDto> createDepartment(
            @Valid @RequestBody CreateDepartmentDto createDepartmentDto
    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(departmentService.createDepartment(createDepartmentDto));
    }

    // GET ALL + FILTER + PAGINATION + SORT
    @GetMapping
    public ResponseEntity<Page<DepartmentResponseDto>> getAllDepartment(
            @RequestParam(
                    value = "pageNumber",
                    defaultValue = "0",
                    required = false
            )
            Integer pageNumber,

            @RequestParam(
                    value = "pageSize",
                    defaultValue = "5",
                    required = false
            )
            Integer pageSize,

            @RequestParam(
                    value = "sortBy",
                    defaultValue = "id",
                    required = false
            )
            String sortBy,

            @RequestParam(
                    value = "sortDir",
                    defaultValue = "asc",
                    required = false
            )
            String sortDir,

            FilterDepartmentDto filterDepartmentDto
    ) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        departmentService.getAllDepartment(
                                pageNumber,
                                pageSize,
                                sortBy,
                                sortDir,
                                filterDepartmentDto
                        )
                );
    }

    // GET BY ID
    @GetMapping("/{departId}")
    public ResponseEntity<DepartmentResponseDto> getAllDepartmentById(
            @PathVariable Long departId
    ) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        departmentService.getAllDepartmentById(departId)
                );
    }

    // PUT - FULL UPDATE
    @PutMapping("/{departId}")
    public ResponseEntity<DepartmentResponseDto> updateDepartment(
            @PathVariable Long departId,
            @Valid @RequestBody UpdateDepartmentDto updateDepartmentDto
    ) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        departmentService.updateById(
                                departId,
                                updateDepartmentDto
                        )
                );
    }

    // PATCH - PARTIAL UPDATE
    @PatchMapping("/{departId}")
    public ResponseEntity<DepartmentResponseDto> patchUpdateDepartment(
            @PathVariable Long departId,
            @RequestBody UpdateDepartmentDto updateDepartmentDto
    ) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        departmentService.patchUpdateDepartment(
                                departId,
                                updateDepartmentDto
                        )
                );
    }

    // DELETE
    @DeleteMapping("/{departId}")
    public ResponseEntity<Void> deleteById(
            @PathVariable Long departId
    ) {

        departmentService.deleteById(departId);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}