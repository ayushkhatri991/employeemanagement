package com.EmployeeManagement.Management.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Payroll {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    @NotNull
    private Employee employee;

    @NotNull
    private Integer month;

    @NotNull
    private Integer year;

    @NotNull
    private Double baseSalary;

    private Double performanceBonus;

    private Double overtimePay;

    private Double deductions;

    @NotNull
    private Double netSalary;

    private LocalDate paymentDate;
}
