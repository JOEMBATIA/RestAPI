package com.joe.restapi.service;

import com.joe.restapi.entity.Department;
import com.joe.restapi.error.DepartmentExistsException;
import com.joe.restapi.error.DepartmentNonExistentException;
import com.joe.restapi.error.DepartmentNotFoundException;
import com.joe.restapi.error.DepartmentPropertiesException;
import com.joe.restapi.repository.DepartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    private final DepartmentRepository departmentRepository;

    private static final Logger log = LoggerFactory.getLogger(DepartmentService.class);

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department saveDepartment(Department department) throws DepartmentPropertiesException, DepartmentExistsException {

        log.info("end point for saving department running ...");

        Optional<Department> departmentInDB = Optional.ofNullable
                (departmentRepository.findByDepartmentNameIgnoreCase(department.getDepartmentName()));

        if (departmentInDB.isPresent()) {
            String exceptionErrorMessage = "Department " + department.getDepartmentName() + " already exists";
            log.error(exceptionErrorMessage);
            throw new DepartmentExistsException(exceptionErrorMessage);

        }
        else {
            if (department.getDepartmentCode().isEmpty() &&
                    department.getDepartmentAddress().isEmpty() && department.getDepartmentName().isEmpty() ){
                String message = "Department creation cannot have empty fields";
                log.error(message);
                throw new DepartmentPropertiesException(message);

            }
        }

        log.info("Execution for endpoint createDepartment in DepartmentService.class is completed");

        return departmentRepository.save(department);


    }

    @Override
    public List<Department> getAllDepartments() {

        log.info("executing end point for fetching all departments in DepartmentServiceImpl.class ...");

        List<Department> departmentList = departmentRepository.findAll();

        log.info("End point execution successfully completed");

        return departmentList;
    }

    @Override
    public Department getDepartmentByName(String departmentName) throws DepartmentNotFoundException {

        log.info("executing end point for fetching a single department inside DepartmentServiceImpl.class ....");
        Optional<Department> department =
                Optional.ofNullable(departmentRepository.findByDepartmentNameIgnoreCase(departmentName));

        if (!department.isPresent()) {
            String message = "Department " + departmentName + " not available";
            log.error(message);
            throw new DepartmentNotFoundException(message);
        }
        log.info("execution for end point for fetching a single department inside DepartmentServiceImpl.class completed");

        return department.get();

    }

    @Override
    public Department updateDepartment(Department department, String departmentName) {

        log.info("executing end point for updating department inside DepartmentServiceImpl.class ...");

        Department departmentDB = departmentRepository.findByDepartmentNameIgnoreCase(departmentName);

        if(departmentName.equals(departmentDB.getDepartmentName())){
            departmentDB.setDepartmentName(departmentName);
            departmentDB.setDepartmentCode(department.getDepartmentCode());
            departmentDB.setDepartmentAddress(department.getDepartmentAddress());
        }
        log.info("execution for end point for updating a single department completed");

        return  departmentRepository.save(departmentDB);
    }


    @Override
    public void deleteDepartmentById(Long departmentId) {

        log.info("executing end point for deleting department in DepartmentServiceImpl.class ...");

        departmentRepository.deleteById(departmentId);
        log.info("end point for deleting department completed");


    }

    @Override
    public Optional<Department> getDepartmentById(Long id) {

        log.info("execution for end point for deleting department inside DepartmentServiceImpl.class ...");

        return departmentRepository.findById(id);
    }

    @Override
    public void deleteDepartmentByName(String departmentName) throws DepartmentNonExistentException {

        log.info("execution for end point for deleting department inside DepartmentServiceImpl.class ...");

        if (departmentName.isEmpty()) {
            String message = "Department " + departmentName + " does not exist";
            log.error(message);
            throw new DepartmentNonExistentException (message);
        }
        departmentRepository.deleteByDepartmentNameIgnoreCase(departmentName);

        log.info("execution for end point for fetching a single department completed");
    }
}
