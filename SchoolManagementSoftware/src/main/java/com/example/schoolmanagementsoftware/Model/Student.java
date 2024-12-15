package com.example.schoolmanagementsoftware.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Check;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Name can't be empty.")
    @Size(min = 5,max = 20, message = "Name length must be between 5-20 characters.")
    @Check(constraints = "length(name)>=5")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;

    @NotNull(message = "Age can't be empty.")
    @Min(value = 18, message = "Age min value is 18.")
    @Check(constraints = "age>=18")
    @Column(columnDefinition = "int not null")
    private Integer age;

    @NotEmpty(message = "Major can't be empty.")
    @Size(min = 5,max = 30, message = "Major length must be between 5-30 characters.")
    @Check(constraints = "length(major)>=5")
    @Column(columnDefinition = "varchar(30) not null")
    private String major;

    @ManyToMany(mappedBy = "students")
    private Set<Course> courses;
}