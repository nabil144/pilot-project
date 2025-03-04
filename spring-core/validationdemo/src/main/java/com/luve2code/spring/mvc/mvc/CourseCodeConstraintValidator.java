package com.luve2code.spring.mvc.mvc;

import com.luve2code.spring.mvc.mvc.validation.CourseCode;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CourseCodeConstraintValidator implements ConstraintValidator<CourseCode,String> {

    private String coursePrefix;

    @Override
    public void initialize(CourseCode theCourseCode) {
    coursePrefix = theCourseCode.value()  ;
    }
    @Override
    public boolean isValid(String theCode, ConstraintValidatorContext constraintValidatorContext) {
        boolean result;

        if(theCode !=null) {
          result= theCode.startsWith(coursePrefix);
        }
        else{
            result = true;
        }
        return result;
    }
}
