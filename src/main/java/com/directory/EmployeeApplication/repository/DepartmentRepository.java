package com.directory.EmployeeApplication.repository;

import com.directory.EmployeeApplication.entity.Department;
import com.directory.EmployeeApplication.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {


}
