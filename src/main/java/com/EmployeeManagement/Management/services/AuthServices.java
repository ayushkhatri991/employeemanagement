package com.EmployeeManagement.Management.services;

import com.EmployeeManagement.Management.dto.AuthResponse;
import com.EmployeeManagement.Management.dto.LoginRequest;
import com.EmployeeManagement.Management.dto.RegisterRequest;
import com.EmployeeManagement.Management.enums.UserRole;
import com.EmployeeManagement.Management.models.User;
import com.EmployeeManagement.Management.repo.UserRepo;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthServices {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtServices jwtService;
    private final com.EmployeeManagement.Management.repo.EmployeeRepo employeeRepo;

    public AuthServices(
            UserRepo userRepo,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            JwtServices jwtService,
            com.EmployeeManagement.Management.repo.EmployeeRepo employeeRepo) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.employeeRepo = employeeRepo;
    }
    public User register( RegisterRequest request) {
    if (userRepo.existsByUsername(request.username())) {
        throw new RuntimeException("Username already exists");
    }
    User user = new User();
    user.setUsername(request.username());
    user.setPassword(passwordEncoder.encode(request.password()));
    user.setRole(request.role() != null ? request.role() : UserRole.USER);
    if (request.employeeId() != null) {
        com.EmployeeManagement.Management.models.Employee emp = employeeRepo.findById(request.employeeId())
            .orElseThrow(() -> new RuntimeException("Employee not found"));
        user.setEmployee(emp);
    }
    return userRepo.save(user);
    }

    public AuthResponse login(@Valid LoginRequest request) {
        User user = userRepo.findByUsername(request.username()).orElseThrow(()->
                new RuntimeException("Username not found"));


        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(),
                        request.password())
        );

        String token = jwtService.generateToken(
                user.getUsername(),
                user.getRole().name()
        );
        return new AuthResponse(
                token,
                user.getUsername(),
                user.getRole().name()
        );
    }
}
