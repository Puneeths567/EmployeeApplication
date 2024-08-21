package com.directory.EmployeeApplication.service;


import com.directory.EmployeeApplication.model.EmployeeDTO;

import java.util.List;

public interface EmployeeService{


    Long addEmployee(EmployeeDTO employeeDTO);

  List<EmployeeDTO> getAllEmployees();

    String deleteEmployee(Long id);

    EmployeeDTO getEmployee(Long id);

    EmployeeDTO updateEmployee(EmployeeDTO employeeDTO, Long id);
}
