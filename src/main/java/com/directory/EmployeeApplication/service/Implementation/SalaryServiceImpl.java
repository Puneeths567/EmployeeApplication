package com.directory.EmployeeApplication.service.Implementation;

import com.directory.EmployeeApplication.entity.Employee;
import com.directory.EmployeeApplication.entity.Salary;
import com.directory.EmployeeApplication.model.SalaryDTO;
import com.directory.EmployeeApplication.repository.EmployeeRepository;
import com.directory.EmployeeApplication.repository.SalaryRepository;
import com.directory.EmployeeApplication.service.SalaryService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SalaryServiceImpl implements SalaryService {

    private static final Logger log = LoggerFactory.getLogger(SalaryServiceImpl.class);
    @Autowired
    private SalaryRepository salaryRepository;

    @Autowired
    private EmployeeRepository employeeRepository;


    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<SalaryDTO> getAllSalaries() {

        List<Salary>  salaryList = salaryRepository.findAll();

        log.info("Getting  the salaries of the all employees");

        return salaryList.stream().map(e->modelMapper.map(e,SalaryDTO.class)).collect(Collectors.toList());
    }

    @Override
    public Long addSalary(SalaryDTO salaryDTO, Long id) {

        Optional<Employee> employeeOptional = employeeRepository.findById(id);

        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();

            // Map the SalaryDTO to the Salary entity
            Salary salary = modelMapper.map(salaryDTO, Salary.class);

            // Set the employee in the salary entity
            salary.setEmployee(employee);

            // Save the salary entity, which now has a valid employee reference
            Salary saved = salaryRepository.save(salary);

            log.info("Salary Details have been Successfully added to database");

            // Return the ID of the saved salary
            return saved.getId();

        } else {
            // If the employee is not found, throw an exception
         throw new RuntimeException("Employee with id " + id + " not found.");
        }
    }

    @Override
    public SalaryDTO updateSalary(SalaryDTO salaryDTO, Long id) {

          Optional<Salary> emp_id = salaryRepository.findById(id);

          if(emp_id.isPresent()){
              Salary e  = emp_id.get();

              Salary sal = modelMapper.map(salaryDTO,Salary.class);
              e.setSalary(sal.getSalary());

              Salary salary = salaryRepository.save(e);

              SalaryDTO salaryDTO1 = modelMapper.map(salary,SalaryDTO.class);

              return salaryDTO1;


          }
          else {
              // If the employee is not found, throw an exception
              throw new RuntimeException("Employee with id " + id + " not found.");
          }



    }

}
