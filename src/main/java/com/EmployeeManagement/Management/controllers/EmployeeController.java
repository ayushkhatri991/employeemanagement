package com.EmployeeManagement.Management.controllers;

import com.EmployeeManagement.Management.models.Employee;
import com.EmployeeManagement.Management.services.EmployeeServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {
    @Autowired
    EmployeeServices services;

        @GetMapping("/employees")
        public ResponseEntity<List<Employee>> getAllEmployee() {
            return ResponseEntity.ok(services.getEmployees());
        }

        @GetMapping("/employees/{id}")
        public ResponseEntity<Employee> getEmployee(@PathVariable long id) {
            Employee employee = services.getEmployeeById(id);
            if (employee == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(employee);
        }

        @PostMapping("/employees")
        public ResponseEntity<Employee> postEmployee(@Valid @RequestBody Employee employee){
            Employee employee1 = services.postEmployee(employee);
            return ResponseEntity.status(HttpStatus.CREATED).body(employee1);

        }

        @PutMapping("/employees/{id}")
        public ResponseEntity<Employee> updateEmployee(@PathVariable long id , @Valid @RequestBody Employee employee){
           Employee updatedEmployee = services.updateEmployee(id,employee);
            if (updatedEmployee == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(updatedEmployee);
        }

        @DeleteMapping("/employees/{id}")
        public ResponseEntity<?> deleteEmployee(@PathVariable long id){
         Employee employee = services.getEmployeeById(id);
         if (employee != null){
            services.deleteEmployee(id);
            return ResponseEntity.ok(employee);
        } else{
             return ResponseEntity.notFound().build();
        }
        }


        @GetMapping("/employees/search")
         public ResponseEntity<List<Employee>> searchEmployee(@RequestParam String keyword){

           return ResponseEntity.ok(services.searchEmployees(keyword));

         }
    }



