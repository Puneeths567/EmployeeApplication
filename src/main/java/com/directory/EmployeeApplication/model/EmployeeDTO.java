package com.directory.EmployeeApplication.model;

import com.directory.EmployeeApplication.entity.Department;
import com.directory.EmployeeApplication.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDTO {



    private Long employeeId;
    private String employeeName;
    private DepartmentDTO department;




}
