package com.example.crud.demo.dao;

import com.example.crud.demo.entity.Student;

import java.util.List;

public interface StudentDAO {

    void save (Student thestudent);
    Student findById(Integer id);
    List<Student> findALL();
    List<Student> findbyLastname(String thelastname);
    void update (Student theStudent);
    void delete(Integer id);
    int deletAll();
}
