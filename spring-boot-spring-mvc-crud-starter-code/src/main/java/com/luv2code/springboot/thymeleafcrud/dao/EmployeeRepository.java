package com.luv2code.springboot.thymeleafcrud.dao;

import com.luv2code.springboot.thymeleafcrud.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    //sort by last name
    public List<Employee> findAllByOrderByLastNameAsc();

}
