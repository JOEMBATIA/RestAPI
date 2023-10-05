package com.joe.restapi.service;

import com.joe.restapi.entity.Department;
import com.joe.restapi.error.DepartmentExistsException;
import com.joe.restapi.error.DepartmentNonExistentException;
import com.joe.restapi.error.DepartmentNotFoundException;
import com.joe.restapi.error.DepartmentPropertiesException;
import com.joe.restapi.repository.DepartmentRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class DepartmentServiceTest {

    @Autowired
    private DepartmentService departmentService;

    @MockBean
    private DepartmentRepository departmentRepository;

    // It makes sures all unwanted resources after all executions of the test are completed
    AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();

    }

    // Success scenario
    @Test
    @DisplayName("Get Data based on valid Department Name")
    public void validateDepartmentName_WhenFound() throws DepartmentNotFoundException {
        // given
        Department department =
                Department.builder().
                        departmentName("IT").
                        departmentAddress("Nairobi").
                        departmentCode("IT-001").
                        build();
        String departmentName = "IT";

        when(departmentRepository.findByDepartmentNameIgnoreCase(department.getDepartmentName())).
                thenReturn(department);
        // when
        Department foundDepartmentname = departmentService.getDepartmentByName("IT");
        // then
        assertNotNull(foundDepartmentname);
        assertEquals(departmentName, foundDepartmentname.getDepartmentName());

    }

    // Failure scenario
    @Test
    @DisplayName("Get Data based on invalid Department Name")
    public void validateDepartmentname_WhenNotFound() {
        // given
        Department department =
                Department.builder().
                        departmentName("IT").
                        departmentAddress("Nairobi").
                        departmentCode("IT-001").
                        build();

        String departmentName = "Arst";

        when(departmentRepository.findByDepartmentNameIgnoreCase(department.getDepartmentName())).
                thenReturn(department);

        // when ... then
        assertThrows(DepartmentNotFoundException.class, ()
                                ->departmentService.getDepartmentByName(departmentName));

    }


    // Success scenario
    @Test
    @DisplayName("Pass a string to the Department repository ")
    public void testDeleteDepartmentByName_ShouldDeleteDepartmentIfExists() throws DepartmentNonExistentException {
        // given -> provided by the setUp() method above
        String departmentName = "IT";
        Department department = new Department();
        department.setDepartmentName(departmentName);

        when(departmentRepository.findByDepartmentNameIgnoreCase(departmentName))
                .thenReturn(department);

        // when
        departmentService.deleteDepartmentByName(departmentName);
        // then
        Mockito.verify(departmentRepository).deleteByDepartmentNameIgnoreCase(anyString());

    }

    // Success scenario
    @Test
    @DisplayName("Validate an array of data exists")
    void testGetAllDepartments_shouldFound() {
        // given

        Department department =
                Department.builder().
                        departmentName("IT").
                        departmentAddress("Nairobi").
                        departmentCode("IT-001").
                        build();

        Department department1 =
                Department.builder().
                        departmentName("Mechanical Engineering").
                        departmentAddress("Nairobi").
                        departmentCode("ME-002").
                        build();

        List<Department> departmentList = Arrays.asList(department1, department);

        // when
        when(departmentRepository.findAll()).thenReturn(departmentList);
        List<Department> foundListOfDepartments =
                departmentService.getAllDepartments();

        // then
        assertNotNull(foundListOfDepartments);
        assertEquals(2, foundListOfDepartments.size());
    }

    // Failure scenario
    @Test
    @DisplayName("Validate an empty array of data exists")
    void testGetAllDepartments_shouldNotFound() {
        // given
        Department department =
                Department.builder().
                        departmentName("IT").
                        departmentAddress("Nairobi").
                        departmentCode("IT-001").
                        build();

        Department department1 =
                Department.builder().
                        departmentName("Mechanical Engineering").
                        departmentAddress("Nairobi").
                        departmentCode("ME-002").
                        build();
        List<Department> departmentList = Arrays.asList(department1, department);

        when(departmentRepository.findAll()).thenReturn(departmentList);
        // when
        List<Department> foundListOfDepartments =
                departmentService.getAllDepartments();
        // then
        assertNotNull(foundListOfDepartments);
        assertNotEquals(0, foundListOfDepartments.size());
    }

    // Success scenario
    @Test
    @DisplayName("Validate an Object is being passed and saved")
    void testCreateDepartment_ShouldCreateAndSaveDepartment() throws DepartmentPropertiesException, DepartmentExistsException {
        // given
        String departmentName = "Mechanical Engineering";
        Department department = Department.builder()
                        .departmentName(departmentName)
                                .departmentCode("ME-010")
                                        .departmentAddress("Kisumu").build();

        // when
        when(departmentRepository.save(department)).thenReturn(department);
        Department departmentToBeSaved =
                departmentService.saveDepartment(department);
        // then
        assertNotNull(departmentToBeSaved);
        assertEquals(departmentToBeSaved.getDepartmentName(), departmentName);
        verify(departmentRepository).save(department);

    }

}