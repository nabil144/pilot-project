package com.luve2code.spring.mvc.mvc;

import com.luve2code.spring.mvc.mvc.validation.CourseCode;
import jakarta.validation.constraints.*;

public class Customer {

    private String firstName;

    @NotNull(message="is required")
    @Size(min=1,message="is required")
    private String LastName= "";

    @NotNull(message="is required")
    @Min(value=0,message="must be greater than or equal to zero ")
    @Max(value=10,message="must be less than or equal to 10 ")
    private Integer freePasses;

    @Pattern(regexp = "^[a-zA-Z0-9]{5}",message="only 5 chars/digits")
    private String postalCode;

    @CourseCode(value = "TOPS",message ="must start with TOPS")
    private String CourseCode;

    public String getCourseCode() {
        return CourseCode;
    }

    public void setCourseCode(String courseCode) {
        CourseCode = courseCode;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Integer getFreePasses() {
        return freePasses;
    }

    public void setFreePasses(Integer freePasses) {
        this.freePasses = freePasses;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

}
