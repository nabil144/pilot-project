package com.luve2code.springcoredemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoControler {
    private Coach myCoach;
@Autowired
    public DemoControler(Coach theCoach){
    myCoach = theCoach;
}
@GetMapping("/dailyworkout")
    public String getDailWorkout(){
    return myCoach.getDailyWourkout();
}
}
