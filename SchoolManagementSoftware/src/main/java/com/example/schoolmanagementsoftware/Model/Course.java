package com.example.schoolmanagementsoftware.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
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
@Setter
@Getter
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Name can't be empty.")
    @Size(min = 5, max = 20, message = "Name length must be between 5-20 characters.")
    @Check(constraints = "length(name)>=5")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;

    @ManyToOne
    @JsonIgnore
    private Teacher teacher;

    @ManyToMany
    @JsonIgnore
    private Set<Student> students;
}