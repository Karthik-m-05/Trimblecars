package com.trimblecars.controller;

import com.trimblecars.model.Car;
import com.trimblecars.model.Lease;
import com.trimblecars.service.CarService;
import com.trimblecars.service.LeaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/owner")
@RequiredArgsConstructor
public class CarOwnerController {
    private final CarService carService;
    private final LeaseService leaseService;

    @PostMapping("/cars")
    public ResponseEntity<Car> registerCar(@RequestBody Car car, @RequestParam Long ownerId) {
        return ResponseEntity.ok(carService.registerCar(car, ownerId));
    }

    @GetMapping("/cars")
    public ResponseEntity<List<Car>> getMyCars(@RequestParam Long ownerId) {
        return ResponseEntity.ok(carService.getCarsByOwner(ownerId));
    }

    @GetMapping("/cars/{carId}")
    public ResponseEntity<Car> getCar(@PathVariable Long carId) {
        return carService.getCar(carId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cars/{carId}/leases")
    public ResponseEntity<List<Lease>> getLeaseHistory(@PathVariable Long carId) {
        return ResponseEntity.ok(leaseService.getLeasesByCar(carId));
    }
}
