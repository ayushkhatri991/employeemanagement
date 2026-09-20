package com.EmployeeManagement.Management.controllers;

import com.EmployeeManagement.Management.models.Payroll;
import com.EmployeeManagement.Management.services.PayrollServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payroll")
public class PayrollController {

    @Autowired
    private PayrollServices services;


    @GetMapping
    public ResponseEntity<List<Payroll>> getAllPayrolls() {

        return ResponseEntity.ok(
                services.getAllPayrolls()
        );
    }


    @GetMapping("/{id}")
    public ResponseEntity<Payroll> getPayrollById(
            @PathVariable long id) {

        Payroll payroll =
                services.getPayrollById(id);

        if (payroll == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(payroll);
    }


    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping("/generate")
    public ResponseEntity<?> generatePayroll(
            @RequestParam long employeeId,
            @RequestParam int month,
            @RequestParam int year) {

        try {

            Payroll generatedPayroll =
                    services.generatePayroll(
                            employeeId,
                            month,
                            year
                    );

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(generatedPayroll);

        } catch (IllegalStateException |
                 IllegalArgumentException e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }


    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePayroll(
            @PathVariable long id) {

        if (services.getPayrollById(id) == null) {
            return ResponseEntity.notFound().build();
        }

        services.deletePayroll(id);

        return ResponseEntity.ok(
                "Payroll deleted successfully."
        );
    }
}