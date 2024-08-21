package com.directory.EmployeeApplication.service.Implementation;

import com.directory.EmployeeApplication.entity.Employee;
import com.directory.EmployeeApplication.entity.Salary;
import com.directory.EmployeeApplication.model.SalaryDTO;
import com.directory.EmployeeApplication.repository.EmployeeRepository;
import com.directory.EmployeeApplication.repository.SalaryRepository;
import com.directory.EmployeeApplication.service.SalaryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SalaryServiceImpl implements SalaryService {

    @Autowired
    private SalaryRepository salaryRepository;

    @Autowired
    private EmployeeRepository employeeRepository;


    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<SalaryDTO> getAllSalaries() {
        List<Salary>  salaryList = salaryRepository.findAll();
        return salaryList.stream().map(e->modelMapper.map(e,SalaryDTO.class)).collect(Collectors.toList());
    }

//    @Override
//    public Long addSalary(SalaryDTO salaryDTO, Long id) {
//
//
//        Long employeeId = salaryDTO.getEmployeeId();
//
//        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
//
//        if (employeeOptional.isPresent()) {
//            Employee employee = employeeOptional.get();
//            // Set the employee in the salary entity
//            salaryDTO.setEmployee(employee);
//            // Save the salary entity, which now has a valid employee reference
//            return salaryRepository.save(salary);
//        } else {
//            throw new RuntimeException("Employee with id " + employeeId + " not found.");
//        }
//    }
//
//        Salary salary = modelMapper.map(salaryDTO,Salary.class);
//        Salary saved = salaryRepository.save(salary);
//
//        return saved.getId();
//    }

    @Override
    public Long addSalary(SalaryDTO salaryDTO, Long id) {

        // Fetch the Employee using the provided employeeId from the SalaryDTO
        Long employeeId = salaryDTO.getEmployeeId();

        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);

        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();

            // Map the SalaryDTO to the Salary entity
            Salary salary = modelMapper.map(salaryDTO, Salary.class);

            // Set the employee in the salary entity
            salary.setEmployee(employee);

            // Save the salary entity, which now has a valid employee reference
            Salary saved = salaryRepository.save(salary);

            // Return the ID of the saved salary
            return saved.getId();
        } else {
            // If the employee is not found, throw an exception
            throw new RuntimeException("Employee with id " + employeeId + " not found.");
        }
    }

}
