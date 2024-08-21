package com.directory.EmployeeApplication.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Salary {

    @Id
    @Column(name = "EMP_ID")
    private Long id;

    @Column(name = "SALARY", nullable = false)
    private Long salary;

    @OneToOne
    @MapsId // Use the Employee's ID as the ID for Salary
    @JoinColumn(name = "EMP_ID")
    private Employee employee;
}
