package com.xenby.demo.controller;

import com.xenby.demo.model.Student;
import com.xenby.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {
    @Autowired
    StudentRepository studentRepository;

    @RequestMapping("/students/{id}")
    public Optional<Student> student(@PathVariable("id") int studentId) {
        Optional<Student> student = studentRepository.findById(studentId);
        return student;
    }

    @RequestMapping("/create-students")
    public String createStudent() {
        Student student = new Student();
        student.setAge(123);
        student.setBirthday(Date.valueOf("2001-03-03"));
        student.setName("小華");
        studentRepository.save(student);

        return "success";
    }
}
