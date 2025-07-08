package com.trimblecars.service;

import com.trimblecars.exception.NotFoundException;
import com.trimblecars.model.Car;
import com.trimblecars.model.User;
import com.trimblecars.repository.CarRepository;
import com.trimblecars.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CarService {
    private final CarRepository carRepository;
    private final UserRepository userRepository;

    public Car registerCar(Car car, Long ownerId) {
        Optional<User> owner = userRepository.findById(ownerId);
        if (owner.isEmpty()) throw new NotFoundException("Owner not found");
        car.setOwner(owner.get());
        car.setStatus("IDEAL");
        Car saved = carRepository.save(car);
        log.info("Car registered: {}", saved);
        return saved;
    }

    public List<Car> getCarsByOwner(Long ownerId) {
        return carRepository.findByOwnerId(ownerId);
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Optional<Car> getCar(Long carId) {
        return carRepository.findById(carId);
    }

    public List<Car> getCarsByStatus(String status) {
        return carRepository.findByStatus(status);
    }
}
