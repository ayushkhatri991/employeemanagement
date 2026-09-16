package com.EmployeeManagement.Management.controllers;

import com.EmployeeManagement.Management.enums.LeaveStatus;
import com.EmployeeManagement.Management.models.Leave;
import com.EmployeeManagement.Management.services.LeaveServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LeaveController {

    @Autowired
    private LeaveServices services;

    @GetMapping("/leaves")
    public ResponseEntity<List<Leave>> getAllLeaves() {
        return ResponseEntity.ok(services.getAllLeaves());
    }
    @GetMapping("/leaves/pending")
    public ResponseEntity<List<Leave>> getPendingLeaves() {
        return ResponseEntity.ok(services.getPendingLeaves());
    }
    @GetMapping("/leaves/{id}")
    public ResponseEntity<Leave> getLeave(@PathVariable long id) {

        Leave leave = services.getLeaveById(id);

        if (leave == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(leave);
    }

    @PostMapping("/leaves")
    public ResponseEntity<Leave> postLeave(@Valid @RequestBody Leave leave) {

        Leave savedLeave = services.postLeave(leave);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedLeave);
    }

    //can do only by admin
    @PutMapping("/leaves/{id}/status")
    public ResponseEntity<Leave> updateLeave(
            @PathVariable long id,
            @RequestParam LeaveStatus status) {

        Leave updatedLeave = services.updateLeave(id, status);

        if (updatedLeave == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedLeave);
    }

    @DeleteMapping("/leaves/{id}")
    public ResponseEntity<?> deleteLeave(@PathVariable long id) {

        Leave leave = services.getLeaveById(id);

        if (leave == null) {
            return ResponseEntity.notFound().build();
        }

        services.deleteLeave(id);

        return ResponseEntity.ok(leave);
    }
}
