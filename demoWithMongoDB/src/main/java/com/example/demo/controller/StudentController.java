package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:8080")
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
@RestController
@Slf4j
public class StudentController {

    @Autowired
    private final StudentService studentService;

    @GetMapping("/findAll")
    public List<Student> getAllStudents(){
        return this.studentService.studentList();
    }

    @PostMapping("/insert/student")
    public ResponseEntity<Student> insertStudent(@RequestBody Student s){
        try {
            if(studentService.emailValidation(s.getEmail())) {
                if (studentService.emailExist(s.getEmail())) {
                    return ResponseEntity.status(HttpStatus.FOUND).build();

                } else {
                    studentService.insertStudent(s);
                    return ResponseEntity.status(HttpStatus.CREATED).build();
                }
            }
        }catch (Exception e){
                log.error("Something went wrong, maybe the email entered its wrong!");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
