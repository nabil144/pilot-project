package com.luve2code.springcoredemo.common;

import org.springframework.stereotype.Component;

@Component
public class BaseballCoach implements Coach{
    @Override
    public String getDailyWourkout() {
        return "baseball coach";
    }
}
