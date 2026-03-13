package com.payment.PhonepayBackend.controller;

import com.payment.PhonepayBackend.dto.*;
import com.payment.PhonepayBackend.service.UserService;

import io.swagger.v3.oas.annotations.Operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Register new user")
    @PostMapping("/register")
    public String registerUser(@RequestBody UserRegistrationRequest request) {
        return userService.registerUser(request);
    }

    @Operation(summary = "User login")
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        return userService.login(request);
    }

    @Operation(summary = "Get user profile")
    @GetMapping("/profile/{upiId}")
    public UserProfileResponse getProfile(@PathVariable String upiId) {
        return userService.getUserProfile(upiId);
    }
}