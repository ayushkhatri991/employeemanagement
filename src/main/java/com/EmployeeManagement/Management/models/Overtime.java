package com.EmployeeManagement.Management.models;

import com.EmployeeManagement.Management.enums.OvertimeStatus;
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
public class Overtime {
    @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        @JoinColumn(name = "employee_id")
        @NotNull
        private Employee employee;

        @NotNull
        private LocalDate date;

        @NotNull
        private Double hours;

        @NotNull
        private Double rate;

        private Double amount;

    @NotNull
    @Enumerated(EnumType.STRING)
    private OvertimeStatus status;
}
