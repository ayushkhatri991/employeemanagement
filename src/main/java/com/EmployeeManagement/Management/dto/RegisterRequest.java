package com.EmployeeManagement.Management.dto;

import com.EmployeeManagement.Management.enums.UserRole;
import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(
        @NotBlank
        String username,

        @NotBlank
        String password,

        Long employeeId,

        UserRole role
) {

}

