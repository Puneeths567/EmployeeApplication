package com.directory.EmployeeApplication.model;

import com.directory.EmployeeApplication.entity.Department;
import com.directory.EmployeeApplication.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepartmentDTO {

    private Long departmentId;
    private String departmentName;



}
