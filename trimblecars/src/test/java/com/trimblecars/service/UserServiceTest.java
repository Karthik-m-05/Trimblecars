package com.trimblecars.service;

import com.trimblecars.exception.NotFoundException;
import com.trimblecars.model.User;
import com.trimblecars.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {
    @Mock UserRepository userRepository;
    @InjectMocks UserService userService;

    @BeforeEach
    void setUp() { MockitoAnnotations.openMocks(this); }

    @Test
    void getUser_userExists_success() {
        User user = new User(); user.setId(1L);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        assertTrue(userService.getUser(1L).isPresent());
    }

    @Test
    void getUser_userNotFound_throws() {
        when(userRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> userService.getUser(2L));
    }
}
