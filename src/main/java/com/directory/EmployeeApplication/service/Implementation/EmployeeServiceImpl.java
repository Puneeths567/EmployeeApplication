package com.directory.EmployeeApplication.service.Implementation;

import com.directory.EmployeeApplication.entity.Department;
import com.directory.EmployeeApplication.entity.Employee;
import com.directory.EmployeeApplication.model.EmployeeDTO;
import com.directory.EmployeeApplication.repository.EmployeeRepository;
import com.directory.EmployeeApplication.service.EmployeeService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.TypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

        List<EmployeeDTO> employee = employeeList.stream()
                .map(e -> modelMapper.map(e, EmployeeDTO.class))
                .collect(Collectors.toList());


//        for(Employee e : employeeList){
//            EmployeeDTO employeeDTO = modelMapper.map(e,EmployeeDTO.class);
//
//                    employee.add(employeeDTO);
//        }

        return employee;

    }

    @Override
    public String deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
        log.info("Successfully deleted the employee Details");
        return "Sucessfully Deleted";
    }

    @Override
    public EmployeeDTO getEmployee(Long id) {

        Optional<Employee> employee=employeeRepository.findById(id);

        EmployeeDTO emp = modelMapper.map(employee,EmployeeDTO.class);

        return emp;
    }

    @Override
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO, Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        Employee emp = null;
        if(employee.isPresent()){
             emp = employee.get();
           Employee e = modelMapper.map(employeeDTO,Employee.class);
           emp.setEmployeeName(e.getEmployeeName());
           emp.setDepartment(e.getDepartment());
           employeeRepository.save(emp);
        }
        EmployeeDTO dto = modelMapper.map(emp,EmployeeDTO.class);
        return dto;
    }
}
