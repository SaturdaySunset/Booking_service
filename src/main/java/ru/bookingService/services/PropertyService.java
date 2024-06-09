package ru.bookingService.services;

import jakarta.persistence.OneToMany;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bookingService.entities.Booking;
import ru.bookingService.entities.MyUser;
import ru.bookingService.entities.Property;
import ru.bookingService.repository.BookingRepository;
import ru.bookingService.repository.PropertyRepository;

import java.util.Date;
import java.util.List;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    public PropertyService(PropertyRepository propertyRepository, List<Booking> bookings) {
        this.propertyRepository = propertyRepository;
        this.bookings = bookings;
    }
    @Transactional
    public void removeProperty(Long id) {
        propertyRepository.deleteById(id);
    }
    public Property getPropertyById(Long id) {
        return propertyRepository.findById(id).orElseThrow();
    }
    public void bookProperty(Property property, MyUser user, Date startDate, Date endDate) {
        Booking booking = new Booking();
        booking.setProperty(property);
        booking.setUser(user);
        booking.setStartDate(startDate);
        booking.setEndDate(endDate);
        bookingRepository.save(booking);
    }
    @OneToMany(mappedBy = "property")
    private List<Booking> bookings;

    public boolean isAvailable(Date startDate, Date endDate) {
        for (Booking booking : bookings) {
            if (booking.getStartDate().before(endDate) && booking.getEndDate().after(startDate)) {
                return false;
            }
        }
        return true;
    }

}