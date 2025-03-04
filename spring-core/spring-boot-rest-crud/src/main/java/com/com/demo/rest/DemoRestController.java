package com.com.demo.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")

public class DemoRestController {

    // ADD CODE FOR /hello end point

    @GetMapping("/hello")
    public String sayHello(){
        return "Hello World";
    }

}
