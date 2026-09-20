package com.EmployeeManagement.Management.controllers;

import com.EmployeeManagement.Management.models.Overtime;
import com.EmployeeManagement.Management.services.OvertimeServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/overtime")
public class OvertimeController {
    @Autowired
    private OvertimeServices services;

    @GetMapping
    public ResponseEntity<List<Overtime>> getAllOvertime() {
        return ResponseEntity.ok(services.getAllOvertime());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Overtime> getOvertimeById(
            @PathVariable long id) {

        Overtime overtime = services.getOvertimeById(id);

        if (overtime == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(overtime);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Overtime> postOvertime(
            @Valid @RequestBody Overtime overtime) {

        Overtime saved = services.postOvertime(overtime);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saved);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Overtime> updateOvertime(
            @PathVariable long id,
            @Valid @RequestBody Overtime overtime) {

        Overtime updated =
                services.updateOvertime(id, overtime);

        if (updated == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updated);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOvertime(
            @PathVariable long id) {

        Overtime overtime = services.getOvertimeById(id);

        if (overtime == null) {
            return ResponseEntity.notFound().build();
        }

        services.deleteOvertime(id);

        return ResponseEntity.ok(overtime);
    }
}
