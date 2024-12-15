package com.example.schoolmanagementsoftware.Controller;

import com.example.schoolmanagementsoftware.Api.ApiResponse;
import com.example.schoolmanagementsoftware.Model.Student;
import com.example.schoolmanagementsoftware.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    //CRUD
    //1.GET
    @GetMapping("/get")
    public ResponseEntity getAllStudents(){
        return ResponseEntity.status(200).body(studentService.getAllStudents());
    }

    //2.POST
    @PostMapping("/add")
    public ResponseEntity addStudent(@RequestBody @Valid Student student){
        studentService.addStudent(student);
        return ResponseEntity.status(200).body(new ApiResponse("New Student Added."));
    }

    //3.UPDATE
    @PutMapping("/update/{id}")
    public ResponseEntity updateStudent(@PathVariable Integer id,@RequestBody @Valid Student student){
        studentService.updateStudent(id,student);
        return ResponseEntity.status(200).body(new ApiResponse("Student Updated."));
    }

    //4.DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteStudent(@PathVariable Integer id){
        studentService.deleteStudent(id);
        return ResponseEntity.status(200).body(new ApiResponse("Student Deleted."));
    }

    //Extra endpoint
    @PutMapping("/changeStudentMajor/{student_id}/{major}")
    public ResponseEntity changeStudentMajor(@PathVariable Integer student_id, @PathVariable String major){
        studentService.changeStudentMajor(student_id,major);
        return ResponseEntity.status(200).body(new ApiResponse("Student Major Changed."));
    }
}