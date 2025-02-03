package com.luve2code.springcoredemo.common;

import org.springframework.stereotype.Component;

@Component
public class BaseballCoach implements Coach{

    public BaseballCoach (){
        System.out.println("In constructor:"+getClass().getSimpleName());
    }

    @Override
    public String getDailyWourkout() {
        return "baseball coach";
    }
}
