package com.trimblecars.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trimblecars.model.User;
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
class EndCustomerControllerTest {
    @Autowired MockMvc mockMvc;
    @Autowired ObjectMapper objectMapper;
    @Autowired UserRepository userRepository;
    Long customerId;

    @BeforeEach
    void setup() {
        userRepository.deleteAll();
        // Use a unique username for each test run to avoid constraint violations
        String uniqueUsername = "customer" + System.currentTimeMillis();
        User customer = User.builder().username(uniqueUsername).password("pass").role("CUSTOMER").build();
        customerId = userRepository.save(customer).getId();
    }

    @Test
    void register_success() throws Exception {
        String uniqueUsername = "newuser" + System.currentTimeMillis();
        User user = User.builder().username(uniqueUsername).password("pass").role("CUSTOMER").build();
        mockMvc.perform(post("/api/customer/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value(uniqueUsername));
    }

    @Test
    void getAvailableCars_empty() throws Exception {
        mockMvc.perform(get("/api/customer/cars"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }
}
