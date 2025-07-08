package com.trimblecars.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trimblecars.model.Car;
import com.trimblecars.model.Lease;
import com.trimblecars.model.User;
import com.trimblecars.repository.CarRepository;
import com.trimblecars.repository.LeaseRepository;
import com.trimblecars.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AdminControllerTest {
    @Autowired MockMvc mockMvc;
    @Autowired ObjectMapper objectMapper;
    @Autowired UserRepository userRepository;
    @Autowired CarRepository carRepository;
    @Autowired LeaseRepository leaseRepository;
    Long adminId;

    @BeforeEach
    void setup() {
        leaseRepository.deleteAll();
        carRepository.deleteAll();
        userRepository.deleteAll();
        User admin = User.builder().username("admin1").password("pass").role("ADMIN").build();
        adminId = userRepository.save(admin).getId();
    }

    @Test
    void getAllCars_empty() throws Exception {
        mockMvc.perform(get("/api/admin/cars"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    void getAllUsers_containsAdmin() throws Exception {
        mockMvc.perform(get("/api/admin/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username").value("admin1"));
    }

    @Test
    void getAllLeases_empty() throws Exception {
        mockMvc.perform(get("/api/admin/leases"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }
}
