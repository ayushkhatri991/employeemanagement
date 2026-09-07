package com.EmployeeManagement.Management.controllers;

import com.EmployeeManagement.Management.models.Performance;
import com.EmployeeManagement.Management.services.PerformanceServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api")
public class PerformanceController {

    @Autowired
    private PerformanceServices services;


    @GetMapping("/performance")
    public ResponseEntity<List<Performance>> getAllPerformance() {
        return ResponseEntity.ok(
                services.getAllPerformance()
        );
    }


    @GetMapping("/performance/{id}")
    public ResponseEntity<Performance> getPerformance(
            @PathVariable long id) {

        Performance performance =
                services.getPerformanceById(id);

        if (performance == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(performance);
    }

    @PostMapping("/performance")
    public ResponseEntity<Performance> postPerformance(
            @Valid @RequestBody Performance performance,
            @RequestParam int month,
            @RequestParam int year) {

        Performance saved =
                services.postPerformance(
                        performance,
                        month,
                        year
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saved);
    }


    @PutMapping("/performance/{id}")
    public ResponseEntity<Performance> updatePerformance(
            @PathVariable long id,
            @Valid @RequestBody Performance performance,
            @RequestParam int month,
            @RequestParam int year) {

        Performance updated =
                services.updatePerformance(
                        id,
                        performance,
                        month,
                        year
                );

        if (updated == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updated);
    }


    @DeleteMapping("/performance/{id}")
    public ResponseEntity<?> deletePerformance(
            @PathVariable long id) {

        Performance performance =
                services.getPerformanceById(id);

        if (performance == null) {
            return ResponseEntity.notFound().build();
        }

        services.deletePerformance(id);

        return ResponseEntity.ok(performance);
    }


}
