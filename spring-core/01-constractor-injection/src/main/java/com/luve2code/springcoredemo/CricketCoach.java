package com.luve2code.springcoredemo;

import org.springframework.stereotype.Component;

@Component
public class CricketCoach implements Coach{

    @Override
    public String getDailyWourkout() {
        return "Practice fast!!!!!";

    }
}
