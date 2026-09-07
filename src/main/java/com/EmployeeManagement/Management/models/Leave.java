package com.EmployeeManagement.Management.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Table(name = "employee_leave")
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Leave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String leaveType;
    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate endDate;
    @NotBlank
    private String reason;
    @NotBlank
    private String status;



    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

}