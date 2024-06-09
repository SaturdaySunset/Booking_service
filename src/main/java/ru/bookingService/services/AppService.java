package ru.bookingService.services;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.bookingService.entities.*;
import ru.bookingService.repository.BookingRepository;
import ru.bookingService.repository.PropertyRepository;
import ru.bookingService.repository.UserRepository;

import java.util.Date;

@Service
@AllArgsConstructor
public class AppService {
    private UserRepository repository;
    private PasswordEncoder passwordEncoder;
    private final BookingRepository bookingRepository;
    private final PropertyRepository propertyRepository;
    private final UserRepository userRepository;


    public void addUser(UserDTO user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        MyUser userEntity = MyUser.builder()
                .name(user.getUsername())
                        .password(user.getPassword())
                                .build();

        repository.save(userEntity);
    }
    public void bookProperty(Long propertyId, Long userId, Date startDate, Date endDate) {
        Property property = propertyRepository.findById(propertyId).orElseThrow();
        MyUser user = getUserById(userId);

        if (property.isAvailable(startDate, endDate)) {
            Booking booking = new Booking();
            booking.setUser(user);
            booking.setProperty(property);
            booking.setStartDate(startDate);
            booking.setEndDate(endDate);

            bookingRepository.save(booking);
        } else {
            throw new RuntimeException("Property is not available for the selected dates");
        }
    }
    private MyUser getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow();
    }
}
