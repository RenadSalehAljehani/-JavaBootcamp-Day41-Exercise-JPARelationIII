package com.example.schoolmanagementsoftware.Service;

import com.example.schoolmanagementsoftware.Api.ApiException;
import com.example.schoolmanagementsoftware.DTO.CourseDTO;
import com.example.schoolmanagementsoftware.DTO.StudentDTO;
import com.example.schoolmanagementsoftware.Model.Course;
import com.example.schoolmanagementsoftware.Model.Student;
import com.example.schoolmanagementsoftware.Model.Teacher;
import com.example.schoolmanagementsoftware.Repository.CourseRepository;
import com.example.schoolmanagementsoftware.Repository.StudentRepository;
import com.example.schoolmanagementsoftware.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    //CRUD
    //1.GET
    public List<CourseDTO> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        List<CourseDTO> courseDTOS = new ArrayList<>();
        for (Course course : courses) {
            CourseDTO courseDTO = new CourseDTO(course.getName());
            courseDTOS.add(courseDTO);
        }
        return courseDTOS;
    }

    //2.POST
    public void addCourse(Course course) {
        courseRepository.save(course);
    }

    //3.UPDATE
    public void updateCourse(Integer id, Course course) {
        Course oldCourse = courseRepository.findCourseById(id);
        if (oldCourse == null) {
            throw new ApiException("Course Not Found.");
        }
        oldCourse.setName(course.getName());
        courseRepository.save(oldCourse);
    }

    //4.DELETE
    public void deleteCourse(Integer id) {
        Course oldCourse = courseRepository.findCourseById(id);
        if (oldCourse == null) {
            throw new ApiException("Course Not Found.");
        }
        courseRepository.delete(oldCourse);
    }

    public void assignCourseToTeacher(Integer teacher_id, Integer course_id) {
        Teacher teacher = teacherRepository.findTeacherById(teacher_id);
        Course course = courseRepository.findCourseById(course_id);
        if (teacher == null || course == null) {
            throw new ApiException("Teacher or Course Not Found.");
        }
        course.setTeacher(teacher);
        teacher.getCourses().add(course);

        teacherRepository.save(teacher);
        courseRepository.save(course);
    }

    public void assignCourseToStudent(Integer student_id, Integer course_id) {
        Student student = studentRepository.findStudentById(student_id);
        Course course = courseRepository.findCourseById(course_id);
        if (student == null || course == null) {
            throw new ApiException("Student or Course Not Found.");
        }
        course.getStudents().add(student);
        student.getCourses().add(course);

        studentRepository.save(student);
        courseRepository.save(course);
    }

    // Extra Endpoint
    // An endpoint that take course id and return the teacher name for that class
    public String getTeacherName(Integer course_id) {
        Course course = courseRepository.findCourseById(course_id);
        if (course == null) {
            throw new ApiException("Course Not Found.");
        }
        return course.getTeacher().getName();
    }

    // An endpoint that takes course id and return the student list
    public List<StudentDTO> getCourseStudents(Integer course_id) {
        Course course = courseRepository.findCourseById(course_id);

        if (course == null) {
            throw new ApiException("Course Not Found.");
        }

        List<StudentDTO> studentDTOS = new ArrayList<>();

        for (Student student : course.getStudents()) {
            List<CourseDTO> courseDTOS = new ArrayList<>();

            for (Course c : student.getCourses()) {
                CourseDTO CourseDTO = new CourseDTO(course.getName());
                courseDTOS.add(CourseDTO);
            }

            StudentDTO studentDTO = new StudentDTO(student.getName(), student.getAge(), student.getMajor(), courseDTOS);
            studentDTOS.add(studentDTO);
        }
        return studentDTOS;
    }
}