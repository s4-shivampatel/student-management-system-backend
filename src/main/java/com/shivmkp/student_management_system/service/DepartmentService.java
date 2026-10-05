package com.shivmkp.student_management_system.service;

import com.shivmkp.student_management_system.dto.CreateDepartmentDto;
import com.shivmkp.student_management_system.dto.DepartmentResponseDto;
import com.shivmkp.student_management_system.dto.FilterDepartmentDto;
import com.shivmkp.student_management_system.dto.UpdateDepartmentDto;
import com.shivmkp.student_management_system.entity.Department;
import com.shivmkp.student_management_system.exception.DepartmentNotFoundException;
import com.shivmkp.student_management_system.repository.DepartmentRepository;
import com.shivmkp.student_management_system.specification.DepartmentSpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    // CREATE
    public DepartmentResponseDto createDepartment(
            CreateDepartmentDto createDepartmentDto
    ) {

        Department department = new Department();

        department.setCode(createDepartmentDto.getCode());
        department.setName(createDepartmentDto.getName());
        department.setDescription(createDepartmentDto.getDescription());

        Department savedDepartment = departmentRepository.save(department);

        return mapToResponseDto(savedDepartment);
    }

    // GET ALL + FILTER + PAGINATION + SORT
    public Page<DepartmentResponseDto> getAllDepartment(
            Integer pageNumber,
            Integer pageSize,
            String sortBy,
            String sortDir,
            FilterDepartmentDto filterDepartmentDto
    ) {

        Sort sort = sortDir.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(
                pageNumber,
                pageSize,
                sort
        );

        Specification<Department> specification =
                DepartmentSpecification.filter(filterDepartmentDto);

        Page<Department> departments =
                departmentRepository.findAll(
                        specification,
                        pageable
                );

        return departments.map(this::mapToResponseDto);
    }

    // GET BY ID
    public DepartmentResponseDto getAllDepartmentById(Long departId) {

        Department department = departmentRepository.findById(departId)
                .orElseThrow(
                        () -> new DepartmentNotFoundException(departId)
                );

        return mapToResponseDto(department);
    }

    // PUT - FULL UPDATE
    public DepartmentResponseDto updateById(
            Long departId,
            UpdateDepartmentDto updateDepartmentDto
    ) {

        Department department = departmentRepository.findById(departId)
                .orElseThrow(
                        () -> new DepartmentNotFoundException(departId)
                );

        department.setCode(updateDepartmentDto.getCode());
        department.setName(updateDepartmentDto.getName());
        department.setDescription(updateDepartmentDto.getDescription());

        Department updatedDepartment =
                departmentRepository.save(department);

        return mapToResponseDto(updatedDepartment);
    }

    // PATCH - PARTIAL UPDATE
    public DepartmentResponseDto patchUpdateDepartment(
            Long departId,
            UpdateDepartmentDto updateDepartmentDto
    ) {

        Department department = departmentRepository.findById(departId)
                .orElseThrow(
                        () -> new DepartmentNotFoundException(departId)
                );

        if (updateDepartmentDto.getCode() != null) {
            department.setCode(updateDepartmentDto.getCode());
        }

        if (updateDepartmentDto.getName() != null) {
            department.setName(updateDepartmentDto.getName());
        }

        if (updateDepartmentDto.getDescription() != null) {
            department.setDescription(
                    updateDepartmentDto.getDescription()
            );
        }

        Department updatedDepartment =
                departmentRepository.save(department);

        return mapToResponseDto(updatedDepartment);
    }

    // DELETE
    public void deleteById(Long departId) {

        departmentRepository.findById(departId)
                .orElseThrow(
                        () -> new DepartmentNotFoundException(departId)
                );

        departmentRepository.deleteById(departId);
    }

    // ENTITY -> RESPONSE DTO
    private DepartmentResponseDto mapToResponseDto(
            Department department
    ) {

        return DepartmentResponseDto.builder()
                .id(department.getId())
                .name(department.getName())
                .code(department.getCode())
                .description(department.getDescription())
                .createdAt(department.getCreatedAt())
                .build();
    }
}