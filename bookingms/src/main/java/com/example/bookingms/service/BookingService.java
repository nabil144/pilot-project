package com.example.bookingms.service;



import com.example.bookingms.dto.CustomResponseDTO;
import com.example.bookingms.entity.Booking;

import java.util.List;

public interface BookingService {

    CustomResponseDTO<Booking> saveBooking(Booking newBooking);

    List<Booking> getBookings();

    Booking updateBooking(Booking booking, int bookingId);

    void deleteBookingById(int id);

}
