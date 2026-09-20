package com.EmployeeManagement.Management.controllers;

import com.EmployeeManagement.Management.dto.AuthResponse;
import com.EmployeeManagement.Management.dto.LoginRequest;
import com.EmployeeManagement.Management.dto.RegisterRequest;
import com.EmployeeManagement.Management.models.User;
import com.EmployeeManagement.Management.services.AuthServices;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthServices authServices;
    public AuthController(AuthServices authServices) {
        this.authServices = authServices;
    }



    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
      User user =  authServices.register(request);
        // Never return the password
        user.setPassword(null);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(user);

    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @Valid @RequestBody LoginRequest request) {

        return ResponseEntity.ok(
                authServices.login(request)
        );
    }


}
