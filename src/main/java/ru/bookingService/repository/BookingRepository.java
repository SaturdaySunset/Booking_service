package ru.bookingService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bookingService.models.Booking;

import java.util.Date;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByPropertyId(Long propertyId);
    List<Booking> findByUserId(Long userId);

}