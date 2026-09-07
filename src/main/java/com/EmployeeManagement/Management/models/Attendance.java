package com.EmployeeManagement.Management.models;

import com.EmployeeManagement.Management.enums.AttendanceEnum;
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
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDate date;

   @NotNull
    @Enumerated(EnumType.STRING)
    private AttendanceEnum status;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    @NotNull
    private Employee employee;


}
