package com.joe.restapi.repository;

import com.joe.restapi.entity.Department;
import com.joe.restapi.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Department findByDepartmentNameIgnoreCase(String departmentName);

    void deleteByDepartmentNameIgnoreCase(String departmentName);
}
