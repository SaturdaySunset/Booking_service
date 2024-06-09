package ru.bookingService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bookingService.entities.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
}