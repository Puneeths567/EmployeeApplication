package com.directory.EmployeeApplication.service;

import com.directory.EmployeeApplication.model.DepartmentDTO;
import com.directory.EmployeeApplication.model.EmployeeDTO;

import java.util.List;

public interface DepartmentService {
    Long addDepartment(DepartmentDTO departmentDTO);

    List<DepartmentDTO> getAllDepartments();

    String deleteByDepartment(Long id);

    DepartmentDTO updateDepartment(DepartmentDTO departmentDTO, Long id);
}
