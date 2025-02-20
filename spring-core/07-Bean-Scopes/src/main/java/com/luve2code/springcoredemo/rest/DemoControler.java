package com.luve2code.springcoredemo.rest;

import com.luve2code.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoControler {
    private Coach myCoach;
    private Coach anothercoach;

@Autowired
    public DemoControler(@Qualifier("cricketCoach") Coach theCoach,
                        @Qualifier("cricketCoach")Coach theAnothercoach){
    System.out.println("In constructor:"+getClass().getSimpleName());
    myCoach = theCoach;
    anothercoach = theAnothercoach;

}
@GetMapping("/dailyworkout")
    public String getDailWorkout(){
    return myCoach.getDailyWourkout();
}
@GetMapping("/check")
    public String check (){
    return "Comparing Beans mycoach==anothercoach :"+(myCoach==anothercoach);
}

}
