package com.shivmkp.student_management_system.service;

import com.shivmkp.student_management_system.dto.CreateDepartmentDto;
import com.shivmkp.student_management_system.dto.FilterDepartmentDto;
import com.shivmkp.student_management_system.dto.UpdateDepartmentDto;
import com.shivmkp.student_management_system.entity.Department;
import com.shivmkp.student_management_system.exception.StudentNotFoundException;
import com.shivmkp.student_management_system.repository.DepartmentRepository;
import com.shivmkp.student_management_system.specification.DepartmentSpecification;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.Nullable;
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

    public Department createDepartment(CreateDepartmentDto createDepartmentDto) {
        Department department=new Department();
        department.setDepartmentCode(createDepartmentDto.getDepartmentCode());
        department.setDepartmentName(createDepartmentDto.getDepartmentName());
        department.setDescription(createDepartmentDto.getDescription());
        department.setIsActive(createDepartmentDto.getIsActive());
        return departmentRepository.save(department);

    }

    public Page<Department> getAllDepartment(Integer pageNumber, Integer pageSize, String sortBy, String sortDir, FilterDepartmentDto filterDepartmentDto) {
        Sort sort=sortDir.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable= PageRequest.of(pageNumber,pageSize,sort);
        Specification<Department> specification= DepartmentSpecification.filter(filterDepartmentDto);
        return departmentRepository.findAll(specification,pageable);
    }
    public Department getAllDepartmentById(Long departId) {
        return departmentRepository.findById(departId).orElseThrow(()->new StudentNotFoundException(departId));
    }

    public Department updateById(Long departId, UpdateDepartmentDto updateDepartmentDto) {

        Department department=departmentRepository.findById(departId).orElseThrow(()-> new StudentNotFoundException(departId));
        department.setDepartmentCode(updateDepartmentDto.getDepartmentCode());
        department.setDepartmentName(updateDepartmentDto.getDepartmentName());
        department.setDescription(updateDepartmentDto.getDescription());
        department.setIsActive(updateDepartmentDto.getIsActive());
        return departmentRepository.save(department);
    }

    public Department patchUpdateDepartment(Long departId, UpdateDepartmentDto updateDepartmentDto) {
        Department department=departmentRepository.findById(departId).orElseThrow(()-> new StudentNotFoundException(departId));
        if(updateDepartmentDto.getDepartmentCode()!=null){
            department.setDepartmentCode(updateDepartmentDto.getDepartmentCode());
        }
        if(updateDepartmentDto.getDepartmentName()!=null){
            department.setDepartmentName(updateDepartmentDto.getDepartmentName());
        }
        if(updateDepartmentDto.getDescription()!=null){
            department.setDescription(updateDepartmentDto.getDescription());
        }
        if(updateDepartmentDto.getIsActive()!=null){
            department.setIsActive(updateDepartmentDto.getIsActive());
        }
        return departmentRepository.save(department);
    }

    public void deleteById(Long departId) {
        departmentRepository.findById(departId).orElseThrow(()-> new StudentNotFoundException(departId));
        departmentRepository.deleteById(departId);
    }
}
