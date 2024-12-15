package com.example.schoolmanagementsoftware.Controller;

import com.example.schoolmanagementsoftware.Api.ApiResponse;
import com.example.schoolmanagementsoftware.DTO.StudentDTO;
import com.example.schoolmanagementsoftware.Model.Course;
import com.example.schoolmanagementsoftware.Service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/course")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    //CRUD
    //1.GET
    @GetMapping("/get")
    public ResponseEntity getAllCourses() {
        return ResponseEntity.status(200).body(courseService.getAllCourses());
    }

    //2.POST
    @PostMapping("/add")
    public ResponseEntity addCourse(@RequestBody @Valid Course course) {
        courseService.addCourse(course);
        return ResponseEntity.status(200).body(new ApiResponse("New Course Added."));
    }

    //3.UPDATE
    @PutMapping("/update/{id}")
    public ResponseEntity updateCourse(@PathVariable Integer id, @RequestBody Course course) {
        courseService.updateCourse(id, course);
        return ResponseEntity.status(200).body(new ApiResponse("Course Updated."));
    }

    //4.DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCourse(@PathVariable Integer id) {
        courseService.deleteCourse(id);
        return ResponseEntity.status(200).body(new ApiResponse("Course Deleted."));
    }

    @PutMapping("/assignCourseToTeacher/{teacher_id}/{course_id}")
    public ResponseEntity assignCourseToTeacher(@PathVariable Integer teacher_id, @PathVariable Integer course_id) {
        courseService.assignCourseToTeacher(teacher_id, course_id);
        return ResponseEntity.status(200).body(new ApiResponse("Course Has Been Assigned To Teacher."));
    }

    @PutMapping("/assignCourseToStudent/{student_id}/{course_id}")
    public ResponseEntity assignCourseToStudent(@PathVariable Integer student_id, @PathVariable Integer course_id) {
        courseService.assignCourseToStudent(student_id, course_id);
        return ResponseEntity.status(200).body(new ApiResponse("Course Has Been Assigned To Student."));
    }

    //Extra Endpoint
    @GetMapping("/getTeacherName/{course_id}")
    public ResponseEntity getTeacherName(@PathVariable Integer course_id) {
        String teacherName = courseService.getTeacherName(course_id);
        return ResponseEntity.status(200).body(teacherName);
    }

    @GetMapping("/getCourseStudents/{course_id}")
    public ResponseEntity getCourseStudents(@PathVariable Integer course_id) {
        List<StudentDTO> courseStudents = courseService.getCourseStudents(course_id);
        return ResponseEntity.status(200).body(courseStudents);
    }
}