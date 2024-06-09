package ru.bookingService.services;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.bookingService.models.*;
import ru.bookingService.repository.BookingRepository;
import ru.bookingService.repository.PropertyRepository;
import ru.bookingService.repository.UserRepository;

import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

@Service
@AllArgsConstructor
public class AppService {
    private List<Application> applications;
    private UserRepository repository;
    private PasswordEncoder passwordEncoder;
    private final BookingRepository bookingRepository;
    private final PropertyRepository propertyRepository;
    private final UserRepository userRepository;

    public List<Application> allApplications() {
        return applications;
    }

    public Application applicationByID(int id) {
        return applications.stream()
                .filter(app -> app.getId() == id)
                .findFirst()
                .orElse(null);
    }

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
