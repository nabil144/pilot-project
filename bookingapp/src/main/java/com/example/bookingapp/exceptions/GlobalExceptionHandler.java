package com.example.bookingapp.exceptions;

import com.example.administration.dto.CustomResponseDTO;
import com.example.administration.exceptions.PersonAlreadyExistsException;
import com.example.administration.exceptions.PersonNotMemberException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PersonAlreadyExistsException.class)
    public ResponseEntity<CustomResponseDTO<String>> handlePersonAlreadyExistsException(PersonAlreadyExistsException ex){
        CustomResponseDTO<String> response = new CustomResponseDTO<>(1, ex.getMessage(), ex.getPersonName());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(PersonNotMemberException.class)
    public ResponseEntity<CustomResponseDTO<?>> handlePersonNotMemberException(PersonNotMemberException ex){
        CustomResponseDTO<String> response = new CustomResponseDTO<>(2, ex.getMessage(), ex.getPersonName());
        System.out.println("Handling PersonNotMemberException: " + ex.getMessage());
        System.out.println(response.getMessage());
        ResponseEntity<CustomResponseDTO<?>> responseEntity = new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
        return responseEntity;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomResponseDTO<String>> handleGenericException(Exception ex){
        CustomResponseDTO<String> response = new CustomResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), null);
        System.out.println(ex.getClass());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
