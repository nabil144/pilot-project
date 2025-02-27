package com.example.administration.exceptions;

import com.example.administration.dto.CustomResponseDTO;
import com.example.administration.enums.ResponseCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class PersonExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PersonAlreadyExistsException.class)
    public ResponseEntity<CustomResponseDTO<String>> handlePersonAlreadyExistsException(PersonAlreadyExistsException ex){
        CustomResponseDTO<String> response = new CustomResponseDTO<>(ResponseCode.PERSON_ALREADY_EXISTS, ex.getMessage(), ex.getPersonName());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(PersonNotMemberException.class)
    public ResponseEntity<CustomResponseDTO<?>> handlePersonNotMemberException(PersonNotMemberException ex){
        CustomResponseDTO<String> response = new CustomResponseDTO<>(ResponseCode.PERSON_NOT_MEMBER, ex.getMessage(), ex.getPersonName());
        System.out.println("Handling PersonNotMemberException: " + ex.getMessage());
        System.out.println(response.getMessage());
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomResponseDTO<String>> handleGenericException(Exception ex){
        CustomResponseDTO<String> response = new CustomResponseDTO<>(ResponseCode.INTERNAL_SERVER_ERROR, ex.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
