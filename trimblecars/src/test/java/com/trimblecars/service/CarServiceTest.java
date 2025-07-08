package com.trimblecars.service;

import com.trimblecars.exception.NotFoundException;
import com.trimblecars.model.Car;
import com.trimblecars.model.User;
import com.trimblecars.repository.CarRepository;
import com.trimblecars.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CarServiceTest {
    @Mock CarRepository carRepository;
    @Mock UserRepository userRepository;
    @InjectMocks CarService carService;

    @BeforeEach
    void setUp() { MockitoAnnotations.openMocks(this); }

    @Test
    void registerCar_ownerExists_success() {
        User owner = new User(); owner.setId(1L);
        Car car = new Car();
        when(userRepository.findById(1L)).thenReturn(Optional.of(owner));
        when(carRepository.save(any(Car.class))).thenAnswer(i -> i.getArgument(0));
        Car saved = carService.registerCar(car, 1L);
        assertEquals(owner, saved.getOwner());
        assertEquals("IDEAL", saved.getStatus());
    }

    @Test
    void registerCar_ownerNotFound_throws() {
        when(userRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> carService.registerCar(new Car(), 2L));
    }
}
