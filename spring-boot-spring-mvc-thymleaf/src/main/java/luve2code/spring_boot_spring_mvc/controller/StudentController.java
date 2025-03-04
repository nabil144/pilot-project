package luve2code.spring_boot_spring_mvc.controller;
import luve2code.spring_boot_spring_mvc.model.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StudentController {

    @Value("${countries}")
    private List<String> countries;

    @Value("${languages}")
    private List<String>languages;

    @Value("${systems}")
    private List<String> systems;


    @GetMapping("/showStudentForm")
    public String showForm(Model theModel) {

        // create a new student object
        Student theStudent = new Student();

        //add student object to the model
        theModel.addAttribute("student",theStudent);
        //add the list of countries to the model
        theModel.addAttribute("countries",countries);
        // add the list of languages to the model
        theModel.addAttribute("languages",languages);
        //add the List of system to the Model
        theModel.addAttribute("systems",systems);


        return "Student-form";
    }
@PostMapping("/processStudentForm")

    public String processForm(@ModelAttribute("student") Student theStudent){
        //log the input data
    System.out.printf("theStudent: "+theStudent.getFirstName()+" "+theStudent.getFirstName());

        return"student-confirmation" ;

}

}
