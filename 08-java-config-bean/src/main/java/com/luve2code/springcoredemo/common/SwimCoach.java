package com.luve2code.springcoredemo.common;

public class SwimCoach implements Coach{

    @Override
    public String getDailyWourkout() {
        System.out.println("in constructor :"+getClass().getSimpleName());
        return "Swim 1000 meters sa a warm up";
    }
}
