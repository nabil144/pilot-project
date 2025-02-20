package com.example.crud.demo.dao;

import com.example.crud.demo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public class StudentDAOImpl implements StudentDAO {

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

    @Override
    public Student findById(Integer id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findALL() {

        //create query
        TypedQuery<Student> theQuery =entityManager.createQuery("FROM Student ordered by lastName asc",Student.class);

        //return query result
        return theQuery.getResultList();
    }

    @Override
    public List<Student> findbyLastname(String thelastname) {
        //create query
        TypedQuery<Student> theQuery = entityManager.createQuery(
                "FROM Student WHERE lastName=:theData",Student.class
        );

        //set query parameter
theQuery.setParameter("theData",thelastname);
        //return query result
        return theQuery.getResultList() ;
    }

    @Override
    @Transactional
    public void update(Student theStudent) {
        entityManager.merge(theStudent);
    }

    @Override
    @Transactional
    public void delete(Integer id) {

        //retrieve the student
        Student thestudent =entityManager.find(Student.class,id);

         //delet the student
        entityManager.remove(thestudent);




    }

    @Override
    @Transactional
    public int deletAll() {
    int numRowsDeleted = entityManager.createQuery("DELETE FROM Student").executeUpdate();
        return numRowsDeleted;
    }
}
