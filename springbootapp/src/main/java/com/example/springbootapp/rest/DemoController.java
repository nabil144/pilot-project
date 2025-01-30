//package com.example.springbootapp.rest;
//
//import com.example.springbootapp.common.Coach;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class DemoController {
//
//    //private field for dependency
//    private Coach myCoach;
//
//    //constructor for dependency injection
//    @Autowired
//    public DemoController(Coach theCoach){
//        myCoach = theCoach;
//    }
//
//    @GetMapping("/dailyworkout")
//    public String getDailyWorkout(){
//        return myCoach.getDailyWorkout();
//    }
//}
