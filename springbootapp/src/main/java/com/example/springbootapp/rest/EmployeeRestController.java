//package com.example.springbootapp.rest;
//
//import com.example.springbootapp.dao.EmployeeDAO;
//import com.example.springbootapp.entity.Employee;
//import com.example.springbootapp.service.EmployeeService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api")
//public class EmployeeRestController {
//
//    private EmployeeService employeeService;
//
//    @Autowired
//    public EmployeeRestController(EmployeeService theEmployeeService){
//        employeeService = theEmployeeService;
//    }
//    //expose /employees
//    @GetMapping("/employees")
//    public List<Employee> findAll(){
//        return employeeService.findAll();
//    }
//
//    @GetMapping("/employees/{employeeId}")
//    public Employee getEmployee(@PathVariable int employeeId){
//        Employee theEmployee = employeeService.findById(employeeId);
//        if(theEmployee==null){
//            throw new RuntimeException("employee id not found "+employeeId);
//        }
//        return theEmployee;
//    }
//
//    @PostMapping("/employees")
//    public Employee addEmployee(@RequestBody Employee theEmployee){
//
//        theEmployee.setId(0);
//        Employee dbEmployee = employeeService.save(theEmployee);
//        return dbEmployee;
//    }
//
//    @PutMapping("/employees")
//    public Employee updateEmployee(@RequestBody Employee theEmployee){
//        Employee dbEmployee = employeeService.save(theEmployee);
//        return dbEmployee;
//    }
//
//    @DeleteMapping("/employees/{employeeId}")
//    public String deleteEmployee(@PathVariable int employeeId) {
//        Employee tempEmployee = employeeService.findById(employeeId);
//        if(tempEmployee==null){
//            throw new RuntimeException("employee id not found "+employeeId);
//        }
//        employeeService.deleteById(employeeId);
//        return "deleted employee with id "+employeeId;
//    }
//}
