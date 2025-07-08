package com.trimblecars.controller;

import com.trimblecars.model.Car;
import com.trimblecars.model.Lease;
import com.trimblecars.model.User;
import com.trimblecars.service.CarService;
import com.trimblecars.service.LeaseService;
import com.trimblecars.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final CarService carService;
    private final UserService userService;
    private final LeaseService leaseService;

    @GetMapping("/cars")
    public ResponseEntity<List<Car>> getAllCars() {
        return ResponseEntity.ok(carService.getAllCars());
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        // For demo, fetch all users (add method in UserService if needed)
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/leases")
    public ResponseEntity<List<Lease>> getAllLeases() {
        // For demo, fetch all leases (add method in LeaseService if needed)
        return ResponseEntity.ok(leaseService.getAllLeases());
    }
}
