package com.luve2code.springcoredemo.common;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
//@Primary
public class CricketCoach implements Coach{

    @Override
    public String getDailyWourkout() {
        return "Practice fast!!!!!";

    }
}
