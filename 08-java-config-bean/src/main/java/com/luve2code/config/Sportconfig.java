package com.luve2code.config;

import com.luve2code.springcoredemo.common.Coach;
import com.luve2code.springcoredemo.common.SwimCoach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Sportconfig {
    @Bean
    public Coach swimCoach(){
        return new SwimCoach();
    }


}
