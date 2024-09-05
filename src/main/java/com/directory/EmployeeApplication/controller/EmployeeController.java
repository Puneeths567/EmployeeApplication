package com.directory.EmployeeApplication.controller;

import com.directory.EmployeeApplication.entity.Employee;
import com.directory.EmployeeApplication.model.EmployeeDTO;
import com.directory.EmployeeApplication.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employee")
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {



    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/add")
    public ResponseEntity<Map<String, String>> addEmployee(@RequestBody EmployeeDTO employeeDTO){

        log.info(" Receiving Employee Details");
        Long id = employeeService.addEmployee(employeeDTO);

        log.info("Employee is created with EmployeeId : {} ",id );

        Map<String, String> response = new HashMap<>();
        response.put("message", "Successfully Added");

        return new ResponseEntity<>(response, HttpStatus.OK);

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
    public ResponseEntity<Map<String, String>> deleteEmployee(@PathVariable Long id){

        log.info(" Deleting Employee details of id : {} ",id);
        String deletedEmployee = employeeService.deleteEmployee(id);
        log.info("Employee data is deleted " );

        Map<String, String> response = new HashMap<>();
        response.put("message", "Successfully Deleted");
        return  new ResponseEntity<>(response, HttpStatus.OK);

    }





}
