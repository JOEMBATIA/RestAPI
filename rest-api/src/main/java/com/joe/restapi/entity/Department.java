package com.joe.restapi.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "department_restapi_tbl")
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Department {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Department Name cannot be empty")
    private String departmentName;

    @NotBlank(message = "Department Code cannot be empty")
    private String departmentCode;

    @NotBlank(message = "Department Address cannot be empty")
    private String departmentAddress;

    public Department() {
    }
}
