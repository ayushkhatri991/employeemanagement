package com.EmployeeManagement.Management.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Performance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Min(1)
    @Max(5)
    private Double taskCompletion;

    @NotNull
    @Min(1)
    @Max(5)
    private Double workQuality;

    // This will be calculated from Attendance
    private Double attendance;

    @NotNull
    @Min(1)
    @Max(5)
    private Double punctuality;

    @NotNull
    @Min(1)
    @Max(5)
    private Double teamwork;

    private Double finalRating;

    @NotNull
    private LocalDate reviewDate;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    @NotNull
    private Employee employee;


}
