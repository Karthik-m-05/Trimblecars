package com.trimblecars.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trimblecars.model.Car;
import com.trimblecars.model.User;
import com.trimblecars.repository.CarRepository;
import com.trimblecars.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CarOwnerControllerTest {
    @Autowired MockMvc mockMvc;
    @Autowired ObjectMapper objectMapper;
    @Autowired UserRepository userRepository;
    @Autowired CarRepository carRepository;
    Long ownerId;

    @BeforeEach
    void setup() {
        carRepository.deleteAll();
        userRepository.deleteAll();
        User owner = User.builder().username("owner1").password("pass").role("OWNER").build();
        ownerId = userRepository.save(owner).getId();
    }

    @Test
    void registerCar_success() throws Exception {
        Car car = Car.builder().model("Toyota").variant("Corolla").registrationNumber("ABC123").build();
        mockMvc.perform(post("/api/owner/cars")
                .param("ownerId", ownerId.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(car)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.model").value("Toyota"));
    }

    @Test
    void registerCar_ownerNotFound() throws Exception {
        Car car = Car.builder().model("Honda").variant("Civic").registrationNumber("XYZ789").build();
        mockMvc.perform(post("/api/owner/cars")
                .param("ownerId", "9999")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(car)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Owner not found"));
    }
}
