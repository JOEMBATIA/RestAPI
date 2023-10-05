package com.joe.restapi.repository;

import com.joe.restapi.entity.Department;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    void setUp() {
        Department department =
                Department.builder()
                        .departmentName("Mechanical Engineering")
                        .departmentCode("ME-010")
                        .departmentAddress("Kisumu")
                        .build();

        entityManager.persist(department);
    }

    // Success Scenario
    @Test
    @DisplayName("Validate valid department from given departmentName")
    void testFindByDepartmentNameIgnoreCase_whenFound() {
        // given -> has been provided in the setUp() method
        // when
        Department department =
                departmentRepository.findByDepartmentNameIgnoreCase("Mechanical Engineering");
        // then
        assertEquals(department.getDepartmentAddress(), "Kisumu");
    }


    //  Failure Scenario
    @Test
    @DisplayName("Validate invalid department from given departmentName")
    void testFindByDepartmentNameIgnoreCase_whenNotFound() {
        // given -> has been provided in the setUp() method
        // when
        Department department =
                departmentRepository.findByDepartmentNameIgnoreCase("Mechanical");
        // then
        assertNull(department);
    }

    @Test
    void testDeleteDepartmentByName_whenFound(){
        // given -> has been provided in the setUp() method
        // when
        departmentRepository.deleteByDepartmentNameIgnoreCase("Mechanical Engineering");
        // then
        assertEquals(departmentRepository.findByDepartmentNameIgnoreCase("Mechanical Engineering"), null);

    }
}