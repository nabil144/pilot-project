package com.example.crud.demo.dao;

import com.example.crud.demo.entity.Student;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class StudentDAOImpl implements TASKDAO {

    //define field for entity manager
    private EntityManager entityManager;

    //inject entity manager using constrctor injection

@Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    //implement save method

    @Override
    @Transactional
    public void save(Student thestudent) {
       entityManager.persist(thestudent);

    }
}
