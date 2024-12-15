package com.example.schoolmanagementsoftware.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OutAddressDTO {
    private String area;

    private String street;

    private Integer buildingNumber;
}