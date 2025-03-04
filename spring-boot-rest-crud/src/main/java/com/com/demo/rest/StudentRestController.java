package com.com.demo.rest;

import com.com.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")

public class StudentRestController {

    private List<Student> theStudents;

    @PostConstruct
    public void LoadData() {

        theStudents = new ArrayList<>();
        theStudents.add(new Student("Poornima", "Petal"));
        theStudents.add(new Student("borhane", "huglo"));
        theStudents.add(new Student("rami", "Smith"));

    }

    @GetMapping("/students")
    public List<Student> getStudent() {

        return theStudents;

    }
    //define endpoints or "/students/{student id}" return student at index

    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId) {
        //

        //check the studentId again list size
        if((studentId >= theStudents.size()) || (studentId < 0)){
            throw new StudentNotFoundException("Student id not found -"+studentId);
        }

        return theStudents.get(studentId);
    }
    //add an exception handler using @ExceptionHandler


}
