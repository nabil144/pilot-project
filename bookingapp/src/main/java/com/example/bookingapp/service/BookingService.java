package com.example.bookingapp.service;

import com.example.administration.dto.CustomResponseDTO;
import com.example.administration.entity.Person;
import com.example.bookingapp.entity.Booking;

import java.util.List;

public interface BookingService {

    CustomResponseDTO<Booking> saveBooking(Booking newBooking);

    List<Booking> getBookings();

    Booking updateBooking(Booking booking, int bookingId);

    void deleteBookingById(int id);

}
