package com.trimblecars.repository;

import com.trimblecars.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByStatus(String status);
    List<Car> findByOwnerId(Long ownerId);
}
