package com.example.springboot.thymeleafdemo.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorldController {

    //controller method to show initial html form
    @RequestMapping("/showForm")
    public String showForm(){
        return "helloworld-form";
    }

    //controller method to process form
    @RequestMapping("/processForm")
    public String processForm(){
        return "helloworld";
    }

    @RequestMapping("/processFormVersionTwo")
    public String letsShout(HttpServletRequest request, Model model){
        //read req param from form
        String theName = request.getParameter("studentName");
        //convert to all caps
        theName = theName.toUpperCase();
        //crate msg
        String result = "HELLO "+theName;
        //add msg to model
        model.addAttribute("message",result);
        return "helloworld";
    }

}
