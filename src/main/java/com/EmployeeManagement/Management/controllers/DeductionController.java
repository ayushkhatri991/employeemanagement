package com.EmployeeManagement.Management.controllers;


import com.EmployeeManagement.Management.models.Deduction;
import com.EmployeeManagement.Management.services.DeductionServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/deductions")
public class DeductionController {

    @Autowired
    private DeductionServices services;

    @GetMapping
    public ResponseEntity<List<Deduction>> getAllDeductions() {
        return ResponseEntity.ok(services.getAllDeductions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Deduction> getDeductionById(
            @PathVariable long id) {

        Deduction deduction =
                services.getDeductionById(id);

        if (deduction == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(deduction);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Deduction> postDeduction(
            @Valid @RequestBody Deduction deduction) {

        Deduction saved = services.postDeduction(deduction);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saved);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")

    public ResponseEntity<Deduction> updateDeduction(
            @PathVariable long id,
            @Valid @RequestBody Deduction deduction) {

        Deduction updated =
                services.updateDeduction(id, deduction);

        if (updated == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updated);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDeduction(
            @PathVariable long id) {

        Deduction deduction =
                services.getDeductionById(id);

        if (deduction == null) {
            return ResponseEntity.notFound().build();
        }

        services.deleteDeduction(id);

        return ResponseEntity.ok(deduction);
    }
}