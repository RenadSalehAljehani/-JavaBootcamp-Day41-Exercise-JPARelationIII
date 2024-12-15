package com.example.schoolmanagementsoftware.Service;

import com.example.schoolmanagementsoftware.Api.ApiException;
import com.example.schoolmanagementsoftware.DTO.CourseDTO;
import com.example.schoolmanagementsoftware.DTO.StudentDTO;
import com.example.schoolmanagementsoftware.Model.Course;
import com.example.schoolmanagementsoftware.Model.Student;
import com.example.schoolmanagementsoftware.Repository.CourseRepository;
import com.example.schoolmanagementsoftware.Repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    // CRUD
    //1.GET
    public List<StudentDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        List<StudentDTO> studentDTOS = new ArrayList<>();

        for (Student student : students) {
            List<CourseDTO> courseDTOS = new ArrayList<>();

            for (Course course : student.getCourses()) {
                CourseDTO CourseDTO = new CourseDTO(course.getName());
                courseDTOS.add(CourseDTO);
            }

            StudentDTO studentDTO = new StudentDTO(student.getName(), student.getAge(), student.getMajor(), courseDTOS);
            studentDTOS.add(studentDTO);
        }
        return studentDTOS;
    }

    //2.POST
    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    //3.UPDATE
    public void updateStudent(Integer id, Student student) {
        Student oldStudent = studentRepository.findStudentById(id);
        if (oldStudent == null) {
            throw new ApiException("Student Not Found.");
        }
        oldStudent.setName(student.getName());
        oldStudent.setAge(student.getAge());
        oldStudent.setMajor(student.getMajor());
        studentRepository.save(oldStudent);
    }

    //4.DELETE
    public void deleteStudent(Integer id) {
        Student oldStudent = studentRepository.findStudentById(id);
        if (oldStudent == null) {
            throw new ApiException("Student Not Found.");
        }
        studentRepository.delete(oldStudent);
    }

    // 5.Extra Endpoint
    // An endpoint that takes student id and major and change the student major
    public void changeStudentMajor(Integer student_id, String major) {
        Student student = studentRepository.findStudentById(student_id);
        if (student == null) {
            throw new ApiException("Student Not Found.");
        }

        if (!student.getCourses().isEmpty()) {
            for (Course course : student.getCourses()) {
                course.getStudents().remove(student);// Remove the student from each course
                courseRepository.save(course);
            }
            student.getCourses().clear(); // Then, clear the student's course list
        }

        student.setMajor(major);
        studentRepository.save(student);
    }
}