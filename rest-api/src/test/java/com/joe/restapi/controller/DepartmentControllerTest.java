package com.joe.restapi.controller;

import com.joe.restapi.entity.Department;
import com.joe.restapi.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

@WebMvcTest(value = DepartmentController.class)
class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService departmentService;

    private Department department;

    @BeforeEach
    void setUp() {

        department = Department.builder()
                .departmentName("ME")
                .departmentCode("ME-010")
                .departmentAddress("Kisumu")
                .id(1L)
                .build();
    }

    // Success case scenario
    @Test
    @DisplayName("Pass valid Body and URL to service")
    void testSaveDepartment_whenValid() throws Exception {
        // given
        Department inputDepartment = Department.builder()
                .departmentName("ME")
                .departmentCode("ME-010")
                .departmentAddress("Kisumu")
                .build();
        // when
        Mockito.when(departmentService.saveDepartment(inputDepartment)).thenReturn(department);

        // then
        mockMvc.perform(MockMvcRequestBuilders.post("/department-api/department")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\t\n" +
                        "\t\t\"departmentName\": \"ME\",\n" +
                        "\t\t\"departmentCode\": \"ME-010\",\n" +
                        "\t\t\"departmentAddress\": \"Kisumu\"\n" +
                        "\t\n" +
                        "} "))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }


    // Failure case scenario
    @Test
    @DisplayName("Pass invalid body and URL to service")
    void testSaveDepartment_whenInvalid() throws Exception {
        Department inputDepartment = Department.builder()
                .departmentName("ME")
                .departmentCode("ME-010")
                .departmentAddress("Kisumu")
                .build();

        Mockito.when(departmentService.saveDepartment(inputDepartment)).thenReturn(department);

        mockMvc.perform(MockMvcRequestBuilders.post("/department-api/department")
                .contentType(MediaType.APPLICATION_JSON)
                .content("")
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}