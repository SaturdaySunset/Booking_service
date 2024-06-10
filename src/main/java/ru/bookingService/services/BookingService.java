package ru.bookingService.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bookingService.entities.Booking;
import ru.bookingService.repository.BookingRepository;

import java.util.List;

@Service
    public class BookingService {

        @Autowired
        private BookingRepository bookingRepository;

        public List<Booking> getAllBookings() {
            return bookingRepository.findAll();
        }
    }
