package com.example.schoolmanagementsoftware.Service;

import com.example.schoolmanagementsoftware.DTO.InAddressDTO;
import com.example.schoolmanagementsoftware.Api.ApiException;
import com.example.schoolmanagementsoftware.DTO.OutAddressDTO;
import com.example.schoolmanagementsoftware.Model.Address;
import com.example.schoolmanagementsoftware.Model.Teacher;
import com.example.schoolmanagementsoftware.Repository.AddressRepository;
import com.example.schoolmanagementsoftware.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;
    private final TeacherRepository teacherRepository;

    //CRUD
    //1.GET
    public List<OutAddressDTO> getAllAddresses(){
        List<Address> addresses = addressRepository.findAll();
        List<OutAddressDTO> outAddressDTOS = new ArrayList<>();
        for(Address address : addresses){
            OutAddressDTO outAddressDTO = new OutAddressDTO(address.getArea(),address.getStreet(),address.getBuildingNumber());
            outAddressDTOS.add(outAddressDTO);
        }
        return outAddressDTOS;
    }

    //2.POST
    public void addAddress(InAddressDTO addressDTO){
        Teacher teacher = teacherRepository.findTeacherById(addressDTO.getTeacher_id());
        if (teacher==null){
            throw new ApiException("Teacher Not Found.");
        }
        Address address = new Address(null,addressDTO.getArea(),addressDTO.getStreet(),addressDTO.getBuildingNumber(),teacher);
        addressRepository.save(address);
    }

    //3.UPDATE
    public void updateAddress(InAddressDTO addressDTO){
        Address address = addressRepository.findAddressById(addressDTO.getTeacher_id());
        if (address==null){
            throw new ApiException("Address Not Found.");
        }
        address.setArea(addressDTO.getArea());
        address.setStreet(addressDTO.getStreet());
        address.setBuildingNumber(addressDTO.getBuildingNumber());
        addressRepository.save(address);
    }
}