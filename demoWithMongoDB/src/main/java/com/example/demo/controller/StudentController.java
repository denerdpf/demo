package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin("http://localhost:8080")
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
@RestController
public class StudentController {

    @Autowired
    private final StudentService studentService;

    @Autowired
    private final StudentRepository repository;

    @GetMapping("/findAll")
    public List<Student> getAllStudents(){
        return this.repository.findAll();
    }

    @PostMapping("/insert/student")
    public ResponseEntity<Student> insertStudent(@RequestBody Student s){
        studentService.insertStudent(s);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
