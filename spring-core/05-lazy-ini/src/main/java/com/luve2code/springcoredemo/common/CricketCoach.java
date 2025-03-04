package com.luve2code.springcoredemo.common;

import org.springframework.stereotype.Component;

@Component
public class CricketCoach implements Coach{

    public CricketCoach(){
        System.out.println("In constructor:"+getClass().getSimpleName());
    }


    @Override
    public String getDailyWourkout() {
        return "Practice fast!!!!!";

    }
}
