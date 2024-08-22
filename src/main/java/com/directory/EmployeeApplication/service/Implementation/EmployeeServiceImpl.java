package com.directory.EmployeeApplication.service.Implementation;

import com.directory.EmployeeApplication.entity.Employee;
import com.directory.EmployeeApplication.exception.CustomException;
import com.directory.EmployeeApplication.model.EmployeeDTO;
import com.directory.EmployeeApplication.repository.EmployeeRepository;
import com.directory.EmployeeApplication.service.EmployeeService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;




    @Override
    public Long addEmployee(EmployeeDTO employeeDTO) {

        log.info("Adding new Employee Request : {}",employeeDTO);

        Employee emp = modelMapper.map(employeeDTO,Employee.class);

        Employee employee = employeeRepository.save(emp);
        return employee.getEmployeeId();

    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {

        List<Employee> employeeList = employeeRepository.findAll();

        log.info("Employee Details received from database");

        return employeeList.stream()
                .map(e -> modelMapper.map(e, EmployeeDTO.class))
                .collect(Collectors.toList());

    }

    @Override
    public String deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(()-> new CustomException("Invalid Employee For Delete :"+id,"NOT_FOUND",404));
        employeeRepository.deleteById(id);
        log.info("Successfully deleted the employee Details");
        return "Successfully Deleted";
    }

    @Override
    public EmployeeDTO getEmployee(Long id) {

        Employee employee=employeeRepository.findById(id)
                .orElseThrow(()-> new CustomException("Employee is not found with Employee id :"+id,"NOT_FOUND",404));

        return modelMapper.map(employee,EmployeeDTO.class);
    }

    @Override
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO, Long id) {


        Employee employee = employeeRepository.findById(id).orElseThrow(()-> new CustomException("Invalid Employee For Update :"+id,"NOT_FOUND",404));
        log.info("Fetched the employee details from the database and updating :{}",employee);


        //Mapping DTO to Entity
        Employee e = modelMapper.map(employeeDTO,Employee.class);

        employee.setEmployeeName(e.getEmployeeName());
        employee.setDepartment(e.getDepartment());
        employeeRepository.save(employee);

        return modelMapper.map(employee,EmployeeDTO.class);
    }
}
