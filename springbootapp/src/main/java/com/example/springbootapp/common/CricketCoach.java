package com.example.springbootapp.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class CricketCoach implements Coach {

    //define init method
//    @PostConstruct
//    public void doMyStartupStuff(){
//        System.out.println("in doMyStartupStuff(): " +getClass().getSimpleName());
//    }
//
//    @PreDestroy
//    public void doMyCleanupStuff(){
//        System.out.println("in doMyCleanupStuff(): " +getClass().getSimpleName());
//    }

    @Override
    public String getDailyWorkout() {
        return "practice fast bowling for 15 minutes";
    }
}
