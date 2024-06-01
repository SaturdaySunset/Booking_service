package ru.bookingService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bookingService.models.MyUser;

import java.util.Optional;

public interface UserRepository extends JpaRepository<MyUser, Long> {
    Optional<MyUser> findByName(String username);
}
