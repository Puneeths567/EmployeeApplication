package com.directory.EmployeeApplication.model;

import com.directory.EmployeeApplication.entity.Salary;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SalaryDTO {

//    Salary sal = new Salary();
    Long id;
    Long salary;
}
