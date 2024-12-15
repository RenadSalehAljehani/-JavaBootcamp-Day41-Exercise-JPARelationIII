package com.example.schoolmanagementsoftware.Controller;

import com.example.schoolmanagementsoftware.Api.ApiResponse;
import com.example.schoolmanagementsoftware.DTO.TeacherDTO;
import com.example.schoolmanagementsoftware.Model.Teacher;
import com.example.schoolmanagementsoftware.Service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/teacher")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;

    //CRUD
    //1.GET
    @GetMapping("/get")
    public ResponseEntity getAllTeachers(){
        return ResponseEntity.status(200).body(teacherService.getAllTeachers());
    }

    //2.POST
    @PostMapping("/add")
    public ResponseEntity addTeacher(@RequestBody @Valid Teacher teacher){
        teacherService.addTeacher(teacher);
        return ResponseEntity.status(200).body(new ApiResponse("New Teacher Added."));
    }

    //3.UPDATE
    @PutMapping("/update/{id}")
    public ResponseEntity updateTeacher(@PathVariable Integer id,@RequestBody @Valid Teacher teacher){
        teacherService.updateTeacher(id,teacher);
        return ResponseEntity.status(200).body(new ApiResponse("Teacher Updated."));
    }

    //4.DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteTeacher(@PathVariable Integer id){
        teacherService.deleteTeacher(id);
        return ResponseEntity.status(200).body(new ApiResponse("Teacher Deleted."));
    }

    //5.Extra Endpoint
    //Get teacher details
    @GetMapping("/getTeacherDetails/{id}")
    public ResponseEntity  getTeacherDetails(@PathVariable Integer id){
        TeacherDTO teacherDTO = teacherService.getTeacherDetails(id);
        return ResponseEntity.status(200).body(teacherDTO);
    }
}