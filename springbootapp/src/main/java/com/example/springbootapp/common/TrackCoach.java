package com.example.springbootapp.common;

import org.springframework.stereotype.Component;

@Component
public class TrackCoach implements Coach{

    @Override
    public String getDailyWorkout(){
        return "run hard 5k";
    }
}
