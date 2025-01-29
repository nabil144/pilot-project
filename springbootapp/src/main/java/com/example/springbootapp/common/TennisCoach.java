package com.example.springbootapp.common;

import org.springframework.stereotype.Component;

@Component
public class TennisCoach implements Coach{

    @Override
    public String getDailyWorkout(){
        return "practice your backhand volley";
    }

}
