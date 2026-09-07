package com.EmployeeManagement.Management.models;

import com.EmployeeManagement.Management.enums.TaskStatus;
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
public class Task {
    @Id
    private long id;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate endDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TaskStatus status  = TaskStatus.PENDING;


}
