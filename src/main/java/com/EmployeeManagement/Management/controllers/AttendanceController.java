package com.EmployeeManagement.Management.controllers;

import com.EmployeeManagement.Management.models.Attendance;
import com.EmployeeManagement.Management.services.AttendanceServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AttendanceController {

    @Autowired
    private AttendanceServices services;

    @GetMapping("/attendance")
    public ResponseEntity<List<Attendance>> getAllAttendance(){
        return ResponseEntity.ok(services.getAllAttendance());
    }

    @GetMapping("/attendance/{id}")
    public ResponseEntity<Attendance> getAttendance(@PathVariable long id) {

        Attendance attendance = services.getAttendanceById(id);

        if (attendance == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(attendance);
    }


    @GetMapping("/attendance/rating/{employeeId}")
    public ResponseEntity<Double> getAttendanceRating(
            @PathVariable long employeeId,
            @RequestParam int month,
            @RequestParam int year) {

        double rating =
                services.calculateAttendanceRating(
                        employeeId, month, year);

        return ResponseEntity.ok(rating);
    }

    @PreAuthorize("hasRole('ADMIN','USER')")
    @PostMapping("/attendance")
    public ResponseEntity<Attendance> postAttendance(@Valid @RequestBody Attendance attendance){
       Attendance attendance1 =  services.postAttendance(attendance);

       return ResponseEntity.status(HttpStatus.CREATED).body(attendance1);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/attendance/{id}")
    public ResponseEntity<Attendance> updateAttendance(
            @PathVariable long id,
           @Valid @RequestBody Attendance attendance) {

        Attendance updated = services.updateAttendance(id, attendance);

        if (updated == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updated);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/attendance/{id}")
    public ResponseEntity<?> deleteAttendance(@PathVariable long id) {

        Attendance attendance = services.getAttendanceById(id);

        if (attendance == null) {
            return ResponseEntity.notFound().build();
        }

        services.deleteAttendance(id);

        return ResponseEntity.ok(attendance);
    }

}
