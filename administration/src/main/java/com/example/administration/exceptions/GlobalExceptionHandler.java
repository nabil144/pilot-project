package com.example.administration.exceptions;

import com.example.administration.dto.CustomResponseDTO;
import com.example.administration.entity.Person;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PersonAlreadyExistsException.class)
    public ResponseEntity<CustomResponseDTO<Person>> handlePersonAlreadyExistsException(PersonAlreadyExistsException ex){
        CustomResponseDTO<Person> response = new CustomResponseDTO<>(1, "this person cannot be created because it already exists", null);
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomResponseDTO<String>> handleGenericException(Exception ex){
        CustomResponseDTO<String> response = new CustomResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "internal server error", null);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
