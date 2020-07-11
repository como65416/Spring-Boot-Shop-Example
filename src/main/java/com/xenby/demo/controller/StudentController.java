package com.xenby.demo.controller;

import com.xenby.demo.model.Student;
import com.xenby.demo.repository.StudentRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Optional;

@RestController
public class StudentController {
    @Autowired
    StudentRepository studentRepository;

    @GetMapping(value="/students/{id}")
    @ApiOperation(
            value="取得指定學生資料",
            response=Student.class
    )
    public Optional<Student> student(@PathVariable("id") int studentId) {
        Optional<Student> student = studentRepository.findById(studentId);
        return student;
    }

    @PostMapping(value="/create-students")
    public String createStudent() {
        Student student = new Student();
        student.setAge(123);
        student.setBirthday(Date.valueOf("2001-03-03"));
        student.setName("小華");
        studentRepository.save(student);

        return "success";
    }
}
