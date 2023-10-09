package com.joe.restapi.service;

import com.joe.restapi.entity.Department;
import com.joe.restapi.error.DepartmentExistsException;
import com.joe.restapi.error.DepartmentNonExistentException;
import com.joe.restapi.error.DepartmentNotFoundException;
import com.joe.restapi.error.DepartmentPropertiesException;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    Department saveDepartment(Department department) throws DepartmentPropertiesException, DepartmentExistsException;

    List<Department> getAllDepartments();

    Department getDepartmentByName(String departmentName) throws DepartmentNotFoundException;

    Department updateDepartment(Department department, String departmentName);

    void deleteDepartmentById(Long departmentId);

    Optional<Department> getDepartmentById(Long id);

    void deleteDepartmentByName(String departmentName) throws DepartmentNonExistentException;
}
