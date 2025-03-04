package com.luv2code.springboot.cruddemo.dao;

import com.luv2code.springboot.cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Repository

public class EmployeeDAOjpaImpl implements EmployeeDAO{

    //defin field for entitymanager
    private  EntityManager entityManager;

     //set up constructor injection
    @Autowired
    public EmployeeDAOjpaImpl(EntityManager theEntityManager){
        entityManager=theEntityManager;

    }

    @Override
    public List<Employee> findAll() {

        //create query
        TypedQuery<Employee> theQuery = entityManager.createQuery("from Employee",Employee.class);

        //execute query and get result list
        List<Employee> employees =theQuery.getResultList();

        //return teh result

        return employees;
    }

    @Override
    public Employee findById(int theId) {

        // get employee
        Employee theEmployee = entityManager.find(Employee.class,theId);

        // return employee
        return theEmployee;
    }

    @Override
    public Employee save(Employee theEmployee) {

        //save employee
        Employee dbEmployee = entityManager.merge(theEmployee);


        //return the dbEmployee
        return dbEmployee;
    }

    @Override
    public void deleteById(int theId) {
        //find employee by id
        Employee employee =entityManager.find(Employee.class,theId);

        // remove employee
        entityManager.remove(employee);


    }
}
