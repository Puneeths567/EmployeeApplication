package com.directory.EmployeeApplication.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "department")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "EMPLOYEE_ID")
    private Long employeeId;

    @Column(name = "EMPLOYEE_NAME", nullable = false)
    private String employeeName;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "DEPARTMENT_ID", nullable = false)
    private Department department;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private Salary salary;
}
