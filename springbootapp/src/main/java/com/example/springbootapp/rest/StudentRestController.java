package com.example.springbootapp.rest;

import com.example.springbootapp.dao.StudentDAO;
import com.example.springbootapp.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> theStudents;

    //define @PostConstruct to load student data only once
    @PostConstruct
    public void loadData(){
        theStudents = new ArrayList<>();
        theStudents.add(new Student("mario","rossi","mario@gmail.com"));
        theStudents.add(new Student("poornima","patel","patel@gmail.com"));
    }

    //define endpoint for "/students" - return list of students

    @GetMapping("/students")
    public List<Student> getStudents(){
        System.out.println("reached students");
        return theStudents;
    }

    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId){
        return theStudents.get(studentId); //just index in list, not actual id
    }


}
