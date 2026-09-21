package com.EmployeeManagement.Management.config;

import com.EmployeeManagement.Management.enums.UserRole;
import com.EmployeeManagement.Management.models.User;
import com.EmployeeManagement.Management.repo.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (userRepo.findByUsername("admin").isEmpty()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole(UserRole.ADMIN);
            userRepo.save(admin);
            System.out.println(">>> Initialized default ADMIN user: username='admin', password='admin123'");
        }
    }
}
