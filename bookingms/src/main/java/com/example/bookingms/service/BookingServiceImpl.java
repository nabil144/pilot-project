package com.example.bookingms.service;

import com.example.bookingms.dto.CustomResponseDTO;
import com.example.bookingms.entity.Booking;
import com.example.bookingms.enums.ResponseCode;
import com.example.bookingms.exception.UnauthorizedException;
import com.example.bookingms.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService{

    private BookingRepository bookingRepository;
    private RestTemplate restTemplate;

    @Value("${service.people.url}")
    private String peopleServiceUrl;

    @Autowired
    public BookingServiceImpl(BookingRepository theBookingRepository, RestTemplate theRestTemplate){
        bookingRepository = theBookingRepository;
        restTemplate = theRestTemplate;
    }

    @Override
    public CustomResponseDTO<Booking> saveBooking(Booking newBooking) {

        String url = peopleServiceUrl + newBooking.getPerson();
        try {
            ResponseEntity<CustomResponseDTO> response = restTemplate.exchange(url, HttpMethod.GET, null, CustomResponseDTO.class);
            CustomResponseDTO customResponseDTO = response.getBody();
            if(customResponseDTO.getCode()== ResponseCode.OK){
                Booking dbBooking = bookingRepository.save(newBooking);
                return new CustomResponseDTO<>(ResponseCode.OK,"successfully created booking", dbBooking);
            }else{
                return new CustomResponseDTO<>(customResponseDTO.getCode(), "failed to create booking", null);
            }
        }catch(HttpClientErrorException e){
            throw new UnauthorizedException("you are not authorized to create a booking");
        }catch(Exception e){
            throw e;
        }
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
