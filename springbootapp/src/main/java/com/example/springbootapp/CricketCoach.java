package com.example.springbootapp;

import org.springframework.stereotype.Component;

@Component
public class CricketCoach implements Coach {

    @Override
    public String getDailyWorkout() {
        return "practice fast bowling for 15 minutes";
    }
}
