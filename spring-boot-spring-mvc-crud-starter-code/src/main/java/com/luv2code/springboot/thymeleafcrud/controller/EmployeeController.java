package com.luv2code.springboot.thymeleafcrud.controller;

import com.luv2code.springboot.thymeleafcrud.entity.Employee;
import com.luv2code.springboot.thymeleafcrud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService theEmployeeService){
        employeeService = theEmployeeService;
    }

    //add mapping for /list
    @GetMapping("/list")
    public String listEmployees(Model theModel){
        //get employees from db
        List<Employee> theEmployees = employeeService.findAll();
        //add employees to model
        theModel.addAttribute("employees", theEmployees);
        return "employees/list-employees";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel){
        Employee theEmployee = new Employee();
        theModel.addAttribute("employee", theEmployee);

        return "employees/employee-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") int theId, Model theModel){
        //get employee from service/db
        Employee theEmployee = employeeService.findById(theId);
        //set employee in model
        theModel.addAttribute("employee", theEmployee);
        //send to form
        return "employees/employee-form";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee theEmployee){
        //save the employee
        employeeService.save(theEmployee);
        //redirect to prevent dupe submission
        return "redirect:/employees/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("employeeId") int theId){
        //delete employee
        employeeService.deleteById(theId);
        //redirect to list
        return "redirect:/employees/list";
    }

}
