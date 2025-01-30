package com.example.springbootapp.dao;

import com.example.springbootapp.entity.Student;

public interface StudentDAO {

    void save(Student theStudent);

    Student findById(Integer id);

}
