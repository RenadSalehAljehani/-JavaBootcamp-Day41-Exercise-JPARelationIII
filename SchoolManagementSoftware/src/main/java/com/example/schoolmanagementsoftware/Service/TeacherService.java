package com.example.schoolmanagementsoftware.Service;

import com.example.schoolmanagementsoftware.Api.ApiException;
import com.example.schoolmanagementsoftware.DTO.*;
import com.example.schoolmanagementsoftware.Model.Address;
import com.example.schoolmanagementsoftware.Model.Course;
import com.example.schoolmanagementsoftware.Model.Teacher;
import com.example.schoolmanagementsoftware.Repository.AddressRepository;
import com.example.schoolmanagementsoftware.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final AddressRepository addressRepository;

    // CRUD
    //1.GET
    public List<TeacherDTO> getAllTeachers() {
        List<Teacher> teachers = teacherRepository.findAll();
        List<TeacherDTO> teacherDTOS = new ArrayList<>();

        for (Teacher teacher : teachers) {
            List<CourseDTO> courseDTOS = new ArrayList<>();

            for (Course course : teacher.getCourses()) {
                CourseDTO CourseDTO = new CourseDTO(course.getName());
                courseDTOS.add(CourseDTO);
            }

            OutAddressDTO outAddressDTO = new OutAddressDTO(teacher.getAddress().getArea(), teacher.getAddress().getStreet(), teacher.getAddress().getBuildingNumber());
            TeacherDTO teacherDTO = new TeacherDTO(teacher.getName(), teacher.getAge(), teacher.getEmail(), teacher.getSalary(), outAddressDTO, courseDTOS);
            teacherDTOS.add(teacherDTO);
        }
        return teacherDTOS;
    }


    //2.POST
    public void addTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    //3.UPDATE
    public void updateTeacher(Integer id, Teacher teacher) {
        Teacher oldTeacher = teacherRepository.findTeacherById(id);
        if (oldTeacher == null) {
            throw new ApiException("Teacher Not Found.");
        }
        oldTeacher.setName(teacher.getName());
        oldTeacher.setAge(teacher.getAge());
        oldTeacher.setEmail(teacher.getEmail());
        oldTeacher.setSalary(teacher.getSalary());
        teacherRepository.save(oldTeacher);
    }

    //4.DELETE
    public void deleteTeacher(Integer id) {
        Teacher oldTeacher = teacherRepository.findTeacherById(id);
        if (oldTeacher == null) {
            throw new ApiException("Teacher Not Found.");
        }

        Address address = addressRepository.findAddressById(oldTeacher.getId());
        if (address == null) {
            throw new ApiException("Address Not Found.");
        }
        oldTeacher.setAddress(null);
        addressRepository.delete(address);
        teacherRepository.delete(oldTeacher);
    }

    // 4.Extra Endpoint
    // An endpoint that takes teacher id and return All teacher details
    public TeacherDTO getTeacherDetails(Integer id) {
        Teacher teacher = teacherRepository.findTeacherById(id);
        if (teacher == null) {
            throw new ApiException("Teacher Not Found.");
        }

        List<CourseDTO> courseDTOS = new ArrayList<>();

        for (Course course : teacher.getCourses()) {
            CourseDTO CourseDTO = new CourseDTO(course.getName());
            courseDTOS.add(CourseDTO);
        }

        OutAddressDTO outAddressDTO = new OutAddressDTO(teacher.getAddress().getArea(), teacher.getAddress().getStreet(), teacher.getAddress().getBuildingNumber());
        TeacherDTO teacherDTO = new TeacherDTO(teacher.getName(), teacher.getAge(), teacher.getEmail(), teacher.getSalary(), outAddressDTO, courseDTOS);
        return teacherDTO;
    }
}