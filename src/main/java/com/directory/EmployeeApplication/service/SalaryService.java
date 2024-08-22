package com.directory.EmployeeApplication.service;

import com.directory.EmployeeApplication.model.SalaryDTO;

import java.util.List;

public interface SalaryService {
    List<SalaryDTO> getAllSalaries();

    Long addSalary(SalaryDTO salaryDTO,Long id);

    SalaryDTO updateSalary(SalaryDTO salaryDTO, Long id);
}
