package com.example.bookingms.controller;

import com.example.bookingms.dto.CustomResponseDTO;
import com.example.bookingms.entity.Booking;
import com.example.bookingms.enums.ResponseCode;
import com.example.bookingms.exception.UnauthorizedException;
import com.example.bookingms.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/list")
    public ResponseEntity<List<Booking>> listBookings(){
        return new ResponseEntity<>(bookingService.getBookings(), HttpStatus.OK);
    }

    @PostMapping("/insert")
    public ResponseEntity<CustomResponseDTO<Booking>> insertBooking(@RequestBody Booking booking){
        try {
            CustomResponseDTO<Booking> customResponseDTO = bookingService.saveBooking(booking);
            if (customResponseDTO.getCode() == ResponseCode.OK) {
                return new ResponseEntity<>(customResponseDTO, HttpStatus.CREATED);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(customResponseDTO);
        } catch (UnauthorizedException ex) {
            CustomResponseDTO<Booking> errorResponse = new CustomResponseDTO<>(ResponseCode.UNAUTHORIZED, ex.getMessage(), null);
            return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
        }
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
