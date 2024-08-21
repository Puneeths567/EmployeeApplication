package com.directory.EmployeeApplication.repository;

import com.directory.EmployeeApplication.entity.Employee;
import com.directory.EmployeeApplication.entity.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaryRepository extends JpaRepository<Salary,Long> {


}
