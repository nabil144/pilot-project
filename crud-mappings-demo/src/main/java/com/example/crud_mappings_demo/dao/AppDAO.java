package com.example.crud_mappings_demo.dao;

import com.example.crud_mappings_demo.entity.Instructor;
import com.example.crud_mappings_demo.entity.InstructorDetail;

public interface AppDAO {

    void save(Instructor theInstructor);

    Instructor findInstructorById(int theId);

    void deleteInstructorById(int theId);

    InstructorDetail findInstructorDetailById(int theId);
}