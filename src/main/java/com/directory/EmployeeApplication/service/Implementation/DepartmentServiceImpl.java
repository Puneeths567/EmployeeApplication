package com.directory.EmployeeApplication.service.Implementation;

import com.directory.EmployeeApplication.entity.Department;
import com.directory.EmployeeApplication.exception.CustomException;
import com.directory.EmployeeApplication.model.DepartmentDTO;
import com.directory.EmployeeApplication.repository.DepartmentRepository;
import com.directory.EmployeeApplication.service.DepartmentService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Long addDepartment(DepartmentDTO departmentDTO) {

        log.info("Adding New Department : {]",departmentDTO);
        Department department = Department.builder()
                .departmentId(departmentDTO.getDepartmentId())
                .departmentName(departmentDTO.getDepartmentName())
                .build();
        try {
            departmentRepository.save(department);

        } catch (RuntimeException ex) {
            // Log the exception details if needed
            log.error("failed to save the Department details " , ex);

            // Throw custom exception
            throw new CustomException("Database error occurred while saving Department. Please try again later.","DATABASE_ERROR",500);
        }

        return department.getDepartmentId();
    }

    @Override
    public List<DepartmentDTO> getAllDepartments() {

        List<Department> departments = departmentRepository.findAll();
        List<DepartmentDTO> departmentDTO = departments.stream().map(e->modelMapper.map(e,DepartmentDTO.class)).collect(Collectors.toList());
        return departmentDTO;
    }

    @Override
    public String deleteByDepartment(Long id)  {
        Department dept = departmentRepository.findById(id).orElseThrow(()->new CustomException("Department is not Found with id : "+id,"NOT_FOUND",404));

        try {
            departmentRepository.deleteById(dept.getDepartmentId());
            log.info("Deleting Department details");
        } catch (DataIntegrityViolationException ex) {
            // Log the exception details if needed
            log.error("Data integrity violation while deleting department with id: " + id, ex);

            // Throw custom exception
            throw new CustomException("Cannot delete  the department because it is referenced by employees","Data integrity violation",409);
        }

        return "Successfully Deleted";
    }

    @Override
    public DepartmentDTO updateDepartment(DepartmentDTO departmentDTO, Long id) {
        Department department = departmentRepository.findById(id).orElseThrow(()->new CustomException("Department is not Found with id : "+id,"NOT_FOUND",404));

        Department e = modelMapper.map(departmentDTO,Department.class);
        e.setDepartmentName(e.getDepartmentName());
        try {
            departmentRepository.save(e);
        }catch (RuntimeException ex){
            // Log the exception details if needed
            log.error("Failed to update the deatils of the department: " , ex);

            // Throw custom exception
            throw new CustomException("Database error occurred while updating department. Please try again later.","DATABASE_ERROR",500);
        }
        return modelMapper.map(e,DepartmentDTO.class);

    }
}
