package com.trimblecars.model;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String model;
    private String variant;
    private String registrationNumber;
    private String status; // IDEAL, ON_LEASE, ON_SERVICE

    @ManyToOne
    @JsonBackReference("car-owner")
    private User owner;

    @OneToMany(mappedBy = "car")
    @JsonManagedReference("car-leases")
    @Builder.Default
    private List<Lease> leases = new ArrayList<>();
}
