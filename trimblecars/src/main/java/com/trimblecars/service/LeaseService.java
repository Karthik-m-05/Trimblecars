package com.trimblecars.service;

import com.trimblecars.model.Lease;
import com.trimblecars.model.Car;
import com.trimblecars.model.User;
import com.trimblecars.repository.LeaseRepository;
import com.trimblecars.repository.CarRepository;
import com.trimblecars.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.trimblecars.exception.NotFoundException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LeaseService {
    private final LeaseRepository leaseRepository;
    private final CarRepository carRepository;
    private final UserRepository userRepository;

    public Lease startLease(Long carId, Long customerId) {
        Car car = carRepository.findById(carId).orElseThrow(() -> new NotFoundException("Car not found"));
        User customer = userRepository.findById(customerId).orElseThrow(() -> new NotFoundException("Customer not found"));
        long activeLeases = leaseRepository.findByCustomerId(customerId).stream().filter(l -> l.getEndDate() == null).count();
        if (activeLeases >= 2) throw new RuntimeException("Max 2 active leases allowed");
        if (!"IDEAL".equals(car.getStatus())) throw new RuntimeException("Car not available for lease");
        car.setStatus("ON_LEASE");
        Lease lease = Lease.builder().car(car).customer(customer).startDate(LocalDateTime.now()).build();
        Lease saved = leaseRepository.save(lease);
        carRepository.save(car);
        log.info("Lease started: {}", saved);
        return saved;
    }

    public Lease endLease(Long leaseId) {
        Lease lease = leaseRepository.findById(leaseId).orElseThrow(() -> new NotFoundException("Lease not found"));
        if (lease.getEndDate() != null) throw new RuntimeException("Lease already ended");
        lease.setEndDate(LocalDateTime.now());
        lease.getCar().setStatus("IDEAL");
        leaseRepository.save(lease);
        carRepository.save(lease.getCar());
        log.info("Lease ended: {}", lease);
        return lease;
    }

    public List<Lease> getLeasesByCustomer(Long customerId) {
        return leaseRepository.findByCustomerId(customerId);
    }

    public List<Lease> getLeasesByCar(Long carId) {
        return leaseRepository.findByCarId(carId);
    }

    public List<Lease> getAllLeases() {
        return leaseRepository.findAll();
    }
}
