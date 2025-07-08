package com.trimblecars.service;

import com.trimblecars.exception.NotFoundException;
import com.trimblecars.model.Car;
import com.trimblecars.model.Lease;
import com.trimblecars.model.User;
import com.trimblecars.repository.CarRepository;
import com.trimblecars.repository.LeaseRepository;
import com.trimblecars.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LeaseServiceTest {
    @Mock LeaseRepository leaseRepository;
    @Mock CarRepository carRepository;
    @Mock UserRepository userRepository;
    @InjectMocks LeaseService leaseService;

    @BeforeEach
    void setUp() { MockitoAnnotations.openMocks(this); }

    @Test
    void startLease_carOrCustomerNotFound_throws() {
        when(carRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> leaseService.startLease(1L, 1L));
    }
}
