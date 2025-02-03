package com.luve2code.springcoredemo.common;

import org.springframework.stereotype.Component;

@Component

public class TennisCoach implements Coach{
    @Override
    public String getDailyWourkout() {
        return "Tennis Coach !!";
    }
}
