package com.joe.restapi.controller;

import com.joe.restapi.entity.Department;
import com.joe.restapi.error.DepartmentExistsException;
import com.joe.restapi.error.DepartmentNonExistentException;
import com.joe.restapi.error.DepartmentNotFoundException;
import com.joe.restapi.error.DepartmentPropertiesException;
import com.joe.restapi.service.DepartmentService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/department-api")
public class DepartmentController {

    private final DepartmentService departmentService;

    private static final Logger logger =
            LoggerFactory.getLogger(DepartmentController.class);

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Value("${initial.message}")
    private String initialMessage;
    @GetMapping
    public String welcome(){
        logger.info("Welcome API call success");
        return initialMessage;
    }

    @PostMapping("/department")
    public ResponseEntity<String> saveDepartment
            (@Valid @RequestBody Department department) throws DepartmentPropertiesException, DepartmentExistsException {

        departmentService.saveDepartment(department);

        String message = "Department created successfully";

        return new ResponseEntity<>(
                message, HttpStatus.CREATED);
    }

    @GetMapping("/department")
    public ResponseEntity<List<Department>> getDepartments(){
        return new ResponseEntity<>(
                departmentService.getAllDepartments(), HttpStatus.OK);
    }

    @GetMapping("/department/name/{departmentName}")
    public ResponseEntity<Department> getDepartmentByName
            (@PathVariable String departmentName) throws DepartmentNotFoundException {

        Department department = departmentService.getDepartmentByName(departmentName);
        return new ResponseEntity<>(department, HttpStatus.FOUND);
    }

    @GetMapping("/department/{id}")
    public ResponseEntity<Optional<Department>> getDepartmentById(@PathVariable Long id){
        return new ResponseEntity<>(departmentService.getDepartmentById(id), HttpStatus.OK);
    }

    @PutMapping("/department/{departmentName}")
    public Department updateDepartment(
            @RequestBody Department department, @PathVariable String departmentName){
        return departmentService.updateDepartment(department, departmentName);
    }

    @DeleteMapping("/department/delete/{departmentName}")
    @Transactional
    public ResponseEntity<String> deleteDepartmentByName(@PathVariable String departmentName) throws DepartmentNonExistentException {
        departmentService.deleteDepartmentByName(departmentName);

        String message = "Department " + departmentName + " deleted successfully";

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/department/{id}")
    public ResponseEntity<String> deleteDepartmentById(@PathVariable Long id){
        departmentService.deleteDepartmentById(id);

        String message =  "Department with ID " + id + " Successfully deleted";

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
