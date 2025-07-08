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
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class EndCustomerController {
    private final UserService userService;
    private final CarService carService;
    private final LeaseService leaseService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        return ResponseEntity.ok(userService.registerUser(user));
    }

    @GetMapping("/cars")
    public ResponseEntity<List<Car>> getAvailableCars() {
        return ResponseEntity.ok(carService.getCarsByStatus("IDEAL"));
    }

    @PostMapping("/lease/start")
    public ResponseEntity<Lease> startLease(@RequestParam Long carId, @RequestParam Long customerId) {
        return ResponseEntity.ok(leaseService.startLease(carId, customerId));
    }

    @PostMapping("/lease/end")
    public ResponseEntity<Lease> endLease(@RequestParam Long leaseId) {
        return ResponseEntity.ok(leaseService.endLease(leaseId));
    }

    @GetMapping("/leases")
    public ResponseEntity<List<Lease>> getMyLeases(@RequestParam Long customerId) {
        return ResponseEntity.ok(leaseService.getLeasesByCustomer(customerId));
    }
}
