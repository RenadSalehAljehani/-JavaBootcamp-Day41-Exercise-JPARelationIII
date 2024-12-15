package com.example.schoolmanagementsoftware.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.Check;

@Data
@AllArgsConstructor

public class InAddressDTO {
    @NotNull(message = "Teacher id can't be null.")
    private Integer teacher_id;

    @NotEmpty(message = "Area can't be empty.")
    @Size(min = 5, max = 30, message = "Area length must be between 5-30 characters.")
    @Check(constraints = "length(area)>=5")
    private String area;

    @NotEmpty(message = "Street can't be empty.")
    @Size(min = 5, max = 30, message = "Street length must be between 5-30 characters.")
    @Check(constraints = "length(street)>=5")
    private String street;

    @NotNull(message = "Building number can't be empty.")
    @Positive(message = "Building number must be a positive number larger than zero.")
    @Check(constraints = "buildingNumber>0")
    private Integer buildingNumber;
}