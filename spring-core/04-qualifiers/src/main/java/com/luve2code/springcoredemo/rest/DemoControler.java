package com.luve2code.springcoredemo.rest;

import com.luve2code.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoControler {
    private Coach myCoach;

@Autowired
    public DemoControler(@Qualifier("baseBallCoach") Coach theCoach){
    myCoach = theCoach;
}
@GetMapping("/dailyworkout")
    public String getDailWorkout(){
    return myCoach.getDailyWourkout();
}
}
