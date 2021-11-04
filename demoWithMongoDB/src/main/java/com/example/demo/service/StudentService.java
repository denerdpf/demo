package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Slf4j
@RequiredArgsConstructor
@Service
public class StudentService{

    @Autowired
    private StudentRepository repository;

    private String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    public static boolean emailValidation(String email, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(email)
                .matches();
    }

    public void insertStudent(Student student){
       repository.findStudentByEmail(student.getEmail()).ifPresentOrElse( s -> {
       }, ()->{
           if(emailValidation(student.getEmail(),regexPattern)) {
               log.info("Student "+student+" inserted successfully");
               repository.insert(student);
           }else{
               log.error("Something went wrong and was not possible to insert the student: " + student);
           }
       });
    }
}
