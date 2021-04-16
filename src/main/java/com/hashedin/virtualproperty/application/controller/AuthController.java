package com.hashedin.virtualproperty.application.controller;

import com.hashedin.virtualproperty.application.request.LoginRequest;
import com.hashedin.virtualproperty.application.request.SignupRequest;
import com.hashedin.virtualproperty.application.response.AuthResponse;
import com.hashedin.virtualproperty.application.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public AuthResponse loginUser(@RequestBody LoginRequest request) {
        return this.authService.loginUser(request.email, request.password);
    }

    @PostMapping("/signup")
    public AuthResponse signupUser(@RequestBody SignupRequest request) {
        return this.authService.signupUser(request.email, request.password, request.name, request.mobile, request.address);
    }
}
