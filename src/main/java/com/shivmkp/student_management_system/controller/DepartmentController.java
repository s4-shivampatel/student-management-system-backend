package com.shivmkp.student_management_system.controller;


import com.shivmkp.student_management_system.dto.CreateDepartmentDto;
import com.shivmkp.student_management_system.dto.DepartmentDto;
import com.shivmkp.student_management_system.dto.FilterDepartmentDto;
import com.shivmkp.student_management_system.dto.UpdateDepartmentDto;
import com.shivmkp.student_management_system.entity.Department;
import com.shivmkp.student_management_system.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
@AllArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<Department> createDepartment(@Valid @RequestBody CreateDepartmentDto createDepartmentDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(departmentService.createDepartment(createDepartmentDto));
    }

    @GetMapping
    public ResponseEntity<Page<Department>> getAllDepartment(@RequestParam(value = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,
                                                             @RequestParam(value = "pageSize",defaultValue = "5",required = false) Integer pageSize,
                                                             @RequestParam(value = "sortBy",defaultValue = "id",required = false) String sortBy,
                                                             @RequestParam(value = "sortDir",defaultValue = "asc",required = false) String sortDir,
                                                             FilterDepartmentDto filterDepartmentDto){
        return  ResponseEntity.status(HttpStatus.OK).body(departmentService.getAllDepartment(pageNumber,pageSize,sortBy,sortDir,filterDepartmentDto));
    }
    @GetMapping("/{departId}")
    public ResponseEntity<Department> getAllDepartmentById(@PathVariable Long departId ){
        return  ResponseEntity.status(HttpStatus.OK).body(departmentService.getAllDepartmentById(departId));
    }
    @PutMapping("/{departId}")
    public ResponseEntity<Department> updateDepartment(@PathVariable Long departId, @RequestBody UpdateDepartmentDto updateDepartmentDto){
        return ResponseEntity.status(HttpStatus.OK).body(departmentService.updateById(departId,updateDepartmentDto));
    }
    @PatchMapping("/{departId}")
    public ResponseEntity<Department> patchUpdateDepartment(@PathVariable Long departId, @RequestBody UpdateDepartmentDto updateDepartmentDto) {
        return ResponseEntity.status(HttpStatus.OK).body(departmentService.patchUpdateDepartment(departId, updateDepartmentDto));
    }
    @DeleteMapping("/{departId}")
    public ResponseEntity<Void> deleteById(@PathVariable Long departId){
        departmentService.deleteById(departId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
