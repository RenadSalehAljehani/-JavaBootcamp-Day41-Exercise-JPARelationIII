package com.example.schoolmanagementsoftware.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

import java.util.Set;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Name can't be empty.")
    @Size(min = 5, max = 10, message = "Name length must be between 5-10 characters.")
    @Check(constraints = "length(name)>=5")
    @Column(columnDefinition = "varchar(10) not null")
    private String name;

    @NotNull(message = "Age can't be empty.")
    @Min(value = 18, message = "Age min value is 18.")
    @Check(constraints = "age>=18")
    @Column(columnDefinition = "int not null")
    private Integer age;

    @Email(message = "Invalid email format.")
    @NotEmpty(message = "Email cant be empty.")
    @Check(constraints = "email LIKE '_%@_%._%'")
    @Column(columnDefinition = "varchar(255) not null unique")
    private String email;

    @NotNull(message = "Salary can't be empty.")
    @Positive(message = "Salary must be a positive number larger than zero.")
    @Check(constraints = "salary>0")
    @Column(columnDefinition = "double not null")
    private Double salary;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Address address;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "teacher")
    private Set<Course> courses;
}