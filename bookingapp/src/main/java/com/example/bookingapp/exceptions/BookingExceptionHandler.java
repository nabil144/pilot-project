package com.example.bookingapp.exceptions;

import com.example.administration.dto.CustomResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class BookingExceptionHandler {

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<CustomResponseDTO<String>> handleHttpClientErrorException(HttpClientErrorException ex) {
        CustomResponseDTO<String> response = new CustomResponseDTO<>(ex.getStatusCode().value(), ex.getMessage(), null);
        return new ResponseEntity<>(response, ex.getStatusCode());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomResponseDTO<String>> handleGenericException(Exception ex){
        CustomResponseDTO<String> response = new CustomResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), null);
        System.out.println(ex.getClass());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
