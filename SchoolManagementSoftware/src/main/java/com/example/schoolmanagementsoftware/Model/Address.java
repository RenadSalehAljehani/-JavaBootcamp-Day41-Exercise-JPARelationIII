package com.example.schoolmanagementsoftware.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Address {
    @Id
    private Integer id;

    @Column(columnDefinition = "varchar(30) not null")
    private String  area;

    @Column(columnDefinition = "varchar(30) not null")
    private String street;

    @Column(columnDefinition = "int not null")
    private Integer buildingNumber;

    @OneToOne
    @MapsId
    @JsonIgnore
    private Teacher teacher;
}