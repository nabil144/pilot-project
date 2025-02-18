package com.example.bookingapp.controller;

import com.example.bookingapp.entity.Booking;
import com.example.bookingapp.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public List<Booking> listBookings(){
        return bookingService.getBookings();
    }

    @PostMapping("/insert")
    public Booking insertBooking(@RequestBody Booking booking){
        return bookingService.saveBooking(booking);
    }

    @DeleteMapping("/delete/{bookingId}")
    public String deleteBooking(@PathVariable int bookingId){
        bookingService.deleteBookingById(bookingId);
        return "deleted";
    }

    @PutMapping("/update/{bookingId}")
    public Booking updateBooking(@RequestBody Booking booking, @PathVariable int bookingId){
        return bookingService.updateBooking(booking,bookingId);
    }

}
