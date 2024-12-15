package com.example.schoolmanagementsoftware.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor

public class TeacherDTO {
    private String name;

    private Integer age;

    private String email;

    private Double salary;

    private OutAddressDTO outAddressDTO;

    private List<CourseDTO> CourseDTOS;
}