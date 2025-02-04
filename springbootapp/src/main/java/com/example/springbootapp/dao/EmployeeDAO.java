package com.example.springbootapp.dao;

import com.example.springbootapp.entity.Employee;

import java.util.List;

public interface EmployeeDAO {

    List<Employee> findAll();
}
