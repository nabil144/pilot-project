package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Instructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


public interface AppDAO {

     void save (Instructor theInstructor) ;

    Instructor findInstructorById(int theId);

    void deleteInstructorById(int theId);
}
