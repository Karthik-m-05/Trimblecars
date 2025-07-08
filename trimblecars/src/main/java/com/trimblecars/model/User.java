package com.trimblecars.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Table(name = "app_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String role; // OWNER, CUSTOMER, ADMIN

    @OneToMany(mappedBy = "owner")
    @JsonManagedReference("car-owner")
    @Builder.Default
    private List<Car> cars = new ArrayList<>();

    @OneToMany(mappedBy = "customer")
    @JsonManagedReference("user-leases")
    @Builder.Default
    private List<Lease> leases = new ArrayList<>();
}
