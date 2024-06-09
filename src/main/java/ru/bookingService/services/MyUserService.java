package ru.bookingService.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.bookingService.repository.UserRepository;
import ru.bookingService.config.MyUserDetails;
import ru.bookingService.entities.MyUser;

import java.util.Optional;

@Service
public class MyUserService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MyUser> user = repository.findByName(username);
        return user.map(MyUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
    }
    public MyUser getUserByUsername(String username) {
        return repository.findByName(username).orElseThrow();
    }
    public MyUser getUserById(Long userId) {
        return repository.findById(userId).orElseThrow();
    }
}
