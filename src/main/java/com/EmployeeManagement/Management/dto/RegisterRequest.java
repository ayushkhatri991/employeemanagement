package com.EmployeeManagement.Management.dto;

import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(
        @NotBlank
        String username,

        @NotBlank
        String password,

        Long employeeId
) {

}
