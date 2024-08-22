package com.directory.EmployeeApplication.controller;

import com.directory.EmployeeApplication.model.EmployeeDTO;
import com.directory.EmployeeApplication.model.SalaryDTO;
import com.directory.EmployeeApplication.service.EmployeeService;
import com.directory.EmployeeApplication.service.SalaryService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salary")
@Log4j2
public class SalaryController {

    @Autowired
    private SalaryService salaryService;



    @PostMapping("/add/{id}")
    public ResponseEntity<Long> addSalary(@RequestBody SalaryDTO salaryDTO,@PathVariable Long id){

        log.info(" Adding Salary Details ");
        Long Empid = salaryService.addSalary(salaryDTO,id);

        log.info("Employee is created with EmployeeId : {} ",id );
        return new ResponseEntity<>(id, HttpStatus.OK);

    }


    @GetMapping("/getAll")
    public ResponseEntity<List<SalaryDTO>> getAllSalaries(){

        log.info(" Fetching Salary Details of All Employees");
        List<SalaryDTO> salaryDTOList = salaryService.getAllSalaries();

        log.info("All Employees Salaries is Fetched " );
        return  new ResponseEntity<>(salaryDTOList, HttpStatus.OK);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SalaryDTO> updateSalary(@RequestBody SalaryDTO salaryDTO,@PathVariable("id") Long id){

        log.info(" Updating Salary Details of employee with id : {}",id);
        SalaryDTO salary = salaryService.updateSalary(salaryDTO,id);

        log.info("Salary Details are updated  for EmployeeId : {} ",id );
        return new ResponseEntity<>(salary, HttpStatus.OK);

    }




}
