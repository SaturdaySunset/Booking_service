package ru.bookingService.services;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.bookingService.models.UserDTO;
import ru.bookingService.repository.UserRepository;
import ru.bookingService.models.Application;
import ru.bookingService.models.MyUser;

import java.util.List;
import java.util.stream.IntStream;

@Service
@AllArgsConstructor
public class AppService {
    private List<Application> applications;
    private UserRepository repository;
    private PasswordEncoder passwordEncoder;

  /*  @PostConstruct
    public void loadAppInDB(){
        Faker faker = new Faker();
        applications = IntStream.rangeClosed(1, 100)
                .mapToObj(i -> Application.builder()
                        .id(i)
                        .name(faker.app().name())
                        .author(faker.app().author())
                        .version(faker.app().version())
                        .build())
                .toList();
    }
*/
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
}
