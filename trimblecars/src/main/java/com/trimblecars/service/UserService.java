package com.trimblecars.service;

import com.trimblecars.exception.NotFoundException;
import com.trimblecars.model.User;
import com.trimblecars.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    public User registerUser(User user) {
        user = userRepository.save(user);
        log.info("User registered: {}", user);
        log.warn("Test WARN log for user registration: {}", user.getUsername());
        log.error("Test ERROR log for user registration: {}", user.getUsername());
        return user;
    }

    public Optional<User> getUser(Long id) {
        return Optional.ofNullable(userRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("User not found")));
    }

    public Optional<User> getUserByUsername(String username) {
        return Optional.ofNullable(userRepository.findByUsername(username)
            .orElseThrow(() -> new NotFoundException("User not found")));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
