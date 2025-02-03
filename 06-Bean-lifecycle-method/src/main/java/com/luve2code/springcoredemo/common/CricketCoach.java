package com.luve2code.springcoredemo.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component

public class CricketCoach implements Coach{

    public CricketCoach(){
        System.out.println("In constructor:"+getClass().getSimpleName());
    }

    //define our init method
    @PostConstruct
    public void doMyStartupStuff(){
        System.out.println("In doMyStartupStuff():"+ getClass().getSimpleName());
    }
    @PreDestroy
    public void doMycleanupStuff(){
        System.out.println("In doMycleanupStuff:"+getClass().getSimpleName());
    }

    //define our destroy method


    @Override
    public String getDailyWourkout() {
        return "Practice fast!!!!!";

    }
}
