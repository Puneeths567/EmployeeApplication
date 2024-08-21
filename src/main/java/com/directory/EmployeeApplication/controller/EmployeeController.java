package com.directory.EmployeeApplication.controller;

import com.directory.EmployeeApplication.entity.Employee;
import com.directory.EmployeeApplication.model.EmployeeDTO;
import com.directory.EmployeeApplication.service.EmployeeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@Log4j2
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/add")
    public ResponseEntity<Long> addEmployee(@RequestBody EmployeeDTO employeeDTO){

        log.info(" Receiving Employee Details");
        Long id = employeeService.addEmployee(employeeDTO);

        log.info("Employee is created with EmployeeId : {} ",id );
        return new ResponseEntity<>(id, HttpStatus.OK);

    }
    @GetMapping("/getAll")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(){

        log.info(" Fetching Employee Details ");
        List<EmployeeDTO> allEmployees = employeeService.getAllEmployees();

        log.info("All Employees Data is Fetched " );
        return  new ResponseEntity<>(allEmployees, HttpStatus.OK);

    }

    @GetMapping("/get/{id}")
    public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable Long id){

        log.info(" Fetching One Employee Details ");
        EmployeeDTO employee = employeeService.getEmployee(id);

        log.info("Employee Data is Fetched " );
        return  new ResponseEntity<>(employee, HttpStatus.OK);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@RequestBody EmployeeDTO employeeDTO,@PathVariable("id") Long id){

        log.info(" Updating Employee Details");
        EmployeeDTO employee = employeeService.updateEmployee(employeeDTO,id);

        log.info("Employee is updated of  EmployeeId : {} ",id );
        return new ResponseEntity<>(employee, HttpStatus.OK);

    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id){

        log.info(" Deleting Employee details of id : {} ",id);
        String deletedEmployee = employeeService.deleteEmployee(id);

        log.info("Employee data is deleted " );
        return  new ResponseEntity<>(deletedEmployee, HttpStatus.OK);

    }





}
