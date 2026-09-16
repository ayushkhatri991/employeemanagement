package com.EmployeeManagement.Management.repo;

import com.EmployeeManagement.Management.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);

        boolean existsByUsername(String username);

}
