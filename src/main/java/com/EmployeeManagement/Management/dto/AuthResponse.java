package com.EmployeeManagement.Management.dto;

public record AuthResponse(
       String token,
       String username,
       String role
) {
}
