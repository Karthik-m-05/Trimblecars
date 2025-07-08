package com.trimblecars.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lease {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonBackReference("car-leases")
    private Car car;

    @ManyToOne
    @JsonBackReference("user-leases")
    private User customer;

    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
