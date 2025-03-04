package com.luv2code.springboot.cruddemo.rest;

import com.luv2code.springboot.cruddemo.dao.EmployeeDAO;
import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")

public class EmployeeRestController {

   // private EmployeeDAO employeeDAO;
    private EmployeeService employeeService;

    //Quick and dirty inject employee dao
    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeservice){
        employeeService=theEmployeeservice;

    }
    //expose "/employee" and return a list of employees
    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    //ADD MAPPING for get employees /{employee id}
    @GetMapping("/employees/{employeeId}")
    public Employee getemployee (@PathVariable int employeeId){

        Employee theEmployee = employeeService.findById(employeeId);
         if (theEmployee==null)
             throw new RuntimeException("Employee id not found -"+employeeId

             );

        return  theEmployee;
    }
    //add mapping for a new employee - add  new employee
    @PostMapping("/employees")
    public Employee addemployee (@RequestBody Employee theEmployee){
        theEmployee.setId(0);
        Employee dbEmployee = employeeService.save(theEmployee);
        return dbEmployee;
    }
    // add mapping for put /employee - update exesting employee

    @PutMapping("/employees")
    public Employee update (@RequestBody Employee theEmployee){
        Employee dbEmployee = employeeService.save(theEmployee);
        return dbEmployee;
    }

}
