package com.xenby.demo.controller;

import com.xenby.demo.model.Student;
import com.xenby.demo.repository.StudentRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {
    @Autowired
    StudentRepository studentRepository;

    @GetMapping(value="/students")
    @ApiOperation("搜尋學生資料")
    public List<Student> searchStudent(@RequestParam(value="", required=false) String name) {
        return studentRepository.findByName(name);
    }

    @PostMapping(value="/students")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("建立學生資料")
    public void createStudent(@PathVariable("id") int studentId, @RequestBody Student student) {
        student.setId(studentId);
        studentRepository.save(student);
    }

    @GetMapping(value="/students/{id}")
    @ApiOperation(
            value="取得指定學生資料",
            response=Student.class
    )
    public Optional<Student> getStudent(@PathVariable("id") int studentId) {
        Optional<Student> student = studentRepository.findById(studentId);
        return student;
    }

    @PutMapping(value="/students/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("更新學生資料")
    public void updateStudent(@RequestBody Student student) {
        studentRepository.save(student);
    }

    @DeleteMapping(value="/students/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("刪除學生資料")
    public void deleteStudent(@PathVariable("id") int studentId) {
        studentRepository.deleteById(studentId);
    }
}
