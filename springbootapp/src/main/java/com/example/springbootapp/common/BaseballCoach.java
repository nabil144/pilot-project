package com.example.springbootapp.common;

import org.springframework.stereotype.Component;

@Component
public class BaseballCoach implements Coach{

    @Override
    public String getDailyWorkout(){
        return "spend 30 mins in batting practice";
    }
}
