package com.directory.EmployeeApplication.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Department {

    @Id
    @Column(name = "DEPARTMENT_ID")
    private Long departmentId;

    @Column(name="DEPARTMENT_NAME",nullable = false)
    private String departmentName;

    @OneToMany(mappedBy = "department",fetch = FetchType.LAZY)
    private List<Employee> employees;


}
