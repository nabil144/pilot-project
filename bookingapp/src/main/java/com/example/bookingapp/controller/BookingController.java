package com.example.bookingapp.controller;

import com.example.administration.dto.CustomResponseDTO;
import com.example.administration.entity.Person;
import com.example.administration.exceptions.PersonAlreadyExistsException;
import com.example.administration.exceptions.PersonNotMemberException;
import com.example.bookingapp.entity.Booking;
import com.example.bookingapp.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {

    private BookingService bookingService;

    @Autowired
    public BookingController(BookingService theBookingService){
        bookingService = theBookingService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Booking>> listBookings(){
        return new ResponseEntity<>(bookingService.getBookings(), HttpStatus.OK);
    }

    @PostMapping("/insert")
    public ResponseEntity<CustomResponseDTO<Booking>> insertBooking(@RequestBody Booking booking){

        CustomResponseDTO<Booking> customResponseDTO = bookingService.saveBooking(booking);

        if(customResponseDTO.getCode()==0){
            return new ResponseEntity<>(customResponseDTO, HttpStatus.CREATED);
        }
        System.out.println("Person not found.");
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(customResponseDTO);
    }

    @DeleteMapping("/delete/{bookingId}")
    public ResponseEntity<String> deleteBooking(@PathVariable int bookingId){
        bookingService.deleteBookingById(bookingId);
        return ResponseEntity.ok()
                .body("deleted");
    }

    @PutMapping("/update/{bookingId}")
    public ResponseEntity<Booking> updateBooking(@RequestBody Booking booking, @PathVariable int bookingId){
        return new ResponseEntity<>(bookingService.updateBooking(booking,bookingId), HttpStatus.OK);
    }

}
