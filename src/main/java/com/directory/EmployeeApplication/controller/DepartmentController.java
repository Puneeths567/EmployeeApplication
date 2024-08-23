package com.directory.EmployeeApplication.controller;

import com.directory.EmployeeApplication.model.DepartmentDTO;
import com.directory.EmployeeApplication.model.EmployeeDTO;
import com.directory.EmployeeApplication.service.DepartmentService;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@RestController
@RequestMapping("/department")
@Slf4j
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/add")
    public ResponseEntity<Long> addDepartment(@RequestBody DepartmentDTO departmentDTO){
        Long id = departmentService.addDepartment(departmentDTO);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments(){

        log.info(" Fetching All Department ");
        List<DepartmentDTO> allDepartments = departmentService.getAllDepartments();

        log.info("All Departments are Fetched " );
        return  new ResponseEntity<>(allDepartments, HttpStatus.OK);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteByDepartment(@PathVariable Long id){

        log.info(" Deleting Department details of id : {} ",id);
        String deletedDepartment = departmentService.deleteByDepartment(id);

        log.info("Department is deleted " );
        log.error("Deleted");
        return  new ResponseEntity<>(deletedDepartment, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DepartmentDTO> updateDepartment(@RequestBody  DepartmentDTO departmentDTO,@PathVariable("id") Long id){

        log.info(" Updating Department Details");
        DepartmentDTO department = departmentService.updateDepartment(departmentDTO,id);

        log.info("Employee is created with EmployeeId : {} ",id );
        return new ResponseEntity<>(department, HttpStatus.OK);

    }

}
