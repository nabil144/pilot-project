package com.example.bookingapp.exception;

import com.example.administration.dto.CustomResponseDTO;
import com.example.administration.enums.ResponseCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class BookingExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<CustomResponseDTO<String>> handleHttpClientErrorException(HttpClientErrorException ex) {
        CustomResponseDTO<String> response = new CustomResponseDTO<>(ResponseCode.INTERNAL_SERVER_ERROR, ex.getMessage(), null);
        return new ResponseEntity<>(response, ex.getStatusCode());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomResponseDTO<String>> handleGenericException(Exception ex){
        CustomResponseDTO<String> response = new CustomResponseDTO<>(ResponseCode.INTERNAL_SERVER_ERROR, ex.getMessage(), null);
        System.out.println(ex.getClass());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
