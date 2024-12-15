package com.example.schoolmanagementsoftware.Controller;

import com.example.schoolmanagementsoftware.DTO.InAddressDTO;
import com.example.schoolmanagementsoftware.Api.ApiResponse;
import com.example.schoolmanagementsoftware.Service.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/address")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;

    //CRUD
    //1.GET
    @GetMapping("/get")
    public ResponseEntity getAllAddresses(){
        return ResponseEntity.status(200).body(addressService.getAllAddresses());
    }

    //2.POST
    @PostMapping("/add")
    public ResponseEntity addAddress(@RequestBody @Valid InAddressDTO addressDTO){
        addressService.addAddress(addressDTO);
        return ResponseEntity.status(200).body(new ApiResponse("New Address Added."));
    }

    //3.UPDATE
    @PutMapping("/update")
    public ResponseEntity updateAddress(@RequestBody @Valid InAddressDTO addressDTO){
        addressService.updateAddress(addressDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Address Updated."));
    }
}