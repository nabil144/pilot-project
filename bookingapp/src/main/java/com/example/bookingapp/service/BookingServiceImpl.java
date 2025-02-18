package com.example.bookingapp.service;

import com.example.bookingapp.entity.Booking;
import com.example.bookingapp.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService{

    private BookingRepository bookingRepository;

    @Autowired
    public BookingServiceImpl(BookingRepository theBookingRepository){
        bookingRepository = theBookingRepository;
    }

    @Override
    public Booking saveBooking(Booking newBooking) {
        return bookingRepository.save(newBooking);
    }

    @Override
    public List<Booking> getBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking updateBooking(Booking booking, int bookingId) {
        Booking dbBooking = bookingRepository.findById(bookingId).get();
        if(booking.getPerson()!=null && !booking.getPerson().isEmpty()){
            dbBooking.setPerson(booking.getPerson());
        }
        if(booking.getBook()!=null && !booking.getBook().isEmpty()){
            dbBooking.setBook(booking.getBook());
        }
        return bookingRepository.save(dbBooking);
    }

    @Override
    public void deleteBookingById(int id) {
        bookingRepository.deleteById(id);
    }
}
