package com.example.demo.controller;

import com.example.demo.exception.ApiRequestExecption;
import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:8080")
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
@RestController
@Slf4j
public class StudentController {

    HttpStatus httpStatus;

    @Autowired
    private final StudentService studentService;

    @GetMapping("/findAll")
    public List<Student> getAllStudents(){
        return this.studentService.studentList();
    }

    @GetMapping("/findById")
    public ResponseEntity<Student> getById(@RequestParam String id){
        try {
            if (id != null) {
                if (studentService.studentListById(id).isEmpty()){
                    httpStatus = HttpStatus.NOT_FOUND;
                }else{
                    httpStatus = HttpStatus.FOUND;
                }
            }else {
                httpStatus = HttpStatus.BAD_REQUEST;
            }
        }catch(Exception e){
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return ResponseEntity.status(httpStatus).build();
    }

    @PostMapping("/insert/student")
    public ResponseEntity<Student> insertStudent(@RequestBody Student s){
        try {
            if(studentService.emailValidation(s.getEmail())) {
                if (studentService.emailExist(s.getEmail())) {
                    log.info("Este email ja esta cadastrado!");
                    httpStatus = HttpStatus.FOUND;

                } else {
                    studentService.insertStudent(s);
                    log.info("Estudante inserido com sucesso!");
                    httpStatus = HttpStatus.CREATED;
                }
            }
        }catch (Exception e){
                log.error("Something went wrong, maybe the email entered its wrong!");
        }
        return ResponseEntity.status(httpStatus).build();
    }
}
