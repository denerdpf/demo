package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Slf4j
@RequiredArgsConstructor
@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    private final String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    public boolean emailValidation(String email) {
        return Pattern.compile(regexPattern)
                .matcher(email)
                .matches();
    }

    public void insertStudent(Student student) {

        try {
            repository.save(student);
        } catch (Exception e) {
              throw e;
        }
    }

    public List<Student> studentList() {
        return repository.findAll();
    }

    public boolean emailExist(String email) {
        boolean exist;

        Optional<Student> studentByEmail = repository.findStudentByEmail(email);
        if (studentByEmail.isEmpty()) {
            log.info("Ok");
            exist = false;
        } else {
            log.info("User already taken this email: " + email);
            exist = true;
        }
        return exist;
    }

    public Optional<Student> studentListById(String id){
        return repository.findById(id);

    }
}
