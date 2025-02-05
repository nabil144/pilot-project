//package com.example.springbootapp.service;
//
//import com.example.springbootapp.dao.EmployeeDAO;
//import com.example.springbootapp.dao.EmployeeRepository;
//import com.example.springbootapp.entity.Employee;
//import jakarta.transaction.Transactional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class EmployeeServiceImpl implements EmployeeService{
//
//    private EmployeeRepository employeeRepository;
//
//    @Autowired
//    public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository){
//        employeeRepository = theEmployeeRepository;
//    }
//
//    @Override
//    public List<Employee> findAll() {
//        return employeeRepository.findAll();
//    }
//
//    @Override
//    public Employee findById(int theId) {
//        Optional<Employee> result = employeeRepository.findById(theId);
//        Employee theEmployee = null;
//        if(result.isPresent()){
//            theEmployee = result.get();
//        }else{
//            throw new RuntimeException("did not find employee with id "+theId);
//        }
//        return theEmployee;
//    }
//
//    @Override
//    public Employee save(Employee theEmployee) {
//        return employeeRepository.save(theEmployee);
//    }
//
//    @Override
//    public void deleteById(int theId) {
//        employeeRepository.deleteById(theId);
//    }
//}
